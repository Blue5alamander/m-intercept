//
//  Shader.vsh
//  Missile-intercept
//
//  Created by Kirit Saelensminde on 12/03/2010.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

attribute vec4 position;
attribute vec4 color;

varying vec4 colorVarying;

uniform float translate;

void main()
{
	gl_Position = position;
	gl_Position.y += sin(translate) / 2.0;
	
	colorVarying = color;
}
