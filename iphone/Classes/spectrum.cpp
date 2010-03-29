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


#include "spectrum.h"
#include <cmath>


mintercept::Spectrum &mintercept::Spectrum::operator ++ () {
    h = h + 360.0f / steps;
    while ( h > 360.0f )
        h -= 360.0f;
    return *this;
}


void mintercept::Spectrum::fill_rgb(unsigned char *rgb) const {
    const float C = l <= 0.5f ? 2.0f * l * s : ( 2.0f - 2.0f * l ) * s;
    const float H = h / 60.0f;
    float Hmod2 = H;
    while ( Hmod2 > 2.0f )
        Hmod2 -= 2.0f;
    const float X = C * ( 1.0f - std::abs(Hmod2 - 1.0f) );
    float r, g, b;
    if ( H < 1.0f ) {
        r = C; g = X; b = 0.0f;
    } else if ( H < 2.0f ) {
        r = X; g = C; b = 0.0f;
    } else if ( H < 3.0f ) {
        r = 0.0f; g = C; b = X;
    } else if ( H < 4.0f ) {
        r = 0.0f; g = X; b = C;
    } else if ( H < 5.0f ) {
        r = X; g = 0.0f; b = C;
    } else {
        r = C; g = 0.0f; b = X;
    }
    const float m = l - 0.5f * C;
    rgb[0] = static_cast<unsigned char>( 255.0f * (r + m) );
    rgb[1] = static_cast<unsigned char>( 255.0f * (g + m) );
    rgb[2] = static_cast<unsigned char>( 255.0f * (b + m) );
}
