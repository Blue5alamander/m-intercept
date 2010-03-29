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


#include "explosions.h"
#include <algorithm>
#include <functional>


mintercept::Explosions::Explosions( std::size_t number, int size )
: explosions( number, size ) {
}


mintercept::Explosion *mintercept::Explosions::reset( int x, int y ) {
    if ( explosions[0].reset(x, y) ) {
        Explosion head = explosions.front();
        explosions.pop_front();
        std::deque<Explosion>::iterator moved = explosions.insert(explosions.end(), head);
        return &*moved;
    } else
        return NULL;
}


bool mintercept::Explosions::tick() {
    std::for_each( explosions.begin(), explosions.end(), std::mem_fun_ref(&Explosion::tick) );
    return true;
}


void mintercept::Explosions::draw( Layer layer ) {
    std::for_each( explosions.begin(), explosions.end(), std::bind2nd(std::mem_fun_ref(&Explosion::draw), layer) );
}
