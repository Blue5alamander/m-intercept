/*
    Copyright 1995-2010, Kirit Saelensminde.
    http://www.kirit.com/Missile%20intercept

    This file is part of Missile intercept.

    Missile intercept is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Missile intercept is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Missile intercept.  If not, see <http://www.gnu.org/licenses/>.
*/


#import "explosion.h"

#include <memory>


namespace  {
    GLfloat degrees2radians(GLfloat degrees) {
        return M_PI * degrees / 180.0f;
    }

    static const std::size_t c_vertices = 360;
    const GLfloat c_radius = 1.0f;

    GLfloat *vertices() {
        static GLfloat data[c_vertices * 2];
        static bool inited = false;
        if ( !inited ) {
            inited = true;
            for ( int i = 0; i != c_vertices * 2; i += 2 ) {
                data[i] = (cos(degrees2radians(i / 2)) * c_radius);
                data[i+1] = (sin(degrees2radians(i / 2)) * c_radius);
            }
        }
        return data;
    }
}


mintercept::Explosion::Explosion( GLfloat ms )
: inuse(false), spectrum(0, 0, 0, 0), max_size( ms ) {
	vertices();
}


bool mintercept::Explosion::reset( int x, int y ) {
	if ( !inuse ) {
		inuse = true;
		pos.x = x;
		pos.y = y;
		spectrum = Spectrum(0, 0.75f, 0.75f, int(max_size));
		radius = 1.0f;
		return true;
	} else
		return false;
}


bool mintercept::Explosion::tick() {
    if ( inuse ) {
        if ( radius < max_size ) {
            ++spectrum;
            radius += 1.0f;
        } else
            inuse = false;
    }
    return inuse;
}	


void mintercept::Explosion::draw( Layer layer ) {
    if (inuse && layer == Element::explosions ) {
        GLubyte ellipseColors[c_vertices * 4];
        for ( std::size_t p = 0; p != c_vertices; ++p ) {
            spectrum.fill_rgb(ellipseColors + p * 4);
            ellipseColors[p * 4 + 3] = 255;
        }
        
        glLoadIdentity();
        glTranslatef(pos.x, pos.y, 0.0f);
        glScalef(radius, radius, 0.0f);
        glVertexPointer(2, GL_FLOAT, 0, vertices());
        glColorPointer(4, GL_UNSIGNED_BYTE, 0, ellipseColors);
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);
        glDrawArrays(GL_TRIANGLE_FAN, 0, c_vertices);
    }
}
