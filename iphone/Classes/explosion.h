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


#import <OpenGLES/ES1/gl.h>
#import "element.h"
#include "spectrum.h"


namespace mintercept {
	class Explosion {
		bool inuse;
		CGPoint pos;
		GLfloat radius, max_size;
		Spectrum spectrum;
		
	public:
		Explosion( GLfloat max_size = 50.0f );
		
		bool reset(int x, int y);
		bool tick();
		void draw();
	};
}


@interface Explosion : NSObject <Element> {
@private
    bool inuse;
    CGPoint pos;
    GLfloat radius, max_size;
    mintercept::Spectrum *spectrum;
 }

-(bool) resetWithX:(int) x withY:(int) y;
-(bool) tick;
-(void) draw;

@end
