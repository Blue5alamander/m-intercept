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
#include "game.h"
#include <algorithm>
#include <functional>


namespace {
    void fill_in( mintercept::Explosions::collection_type &collection, const mintercept::Explosion &with, std::size_t number ) {
        for ( std::size_t n = 0; n != number; ++n )
            collection.push_back( mintercept::Explosions::explosion_ptr( new mintercept::Explosion( with ) ) );
    }
}


mintercept::Explosions::Explosions( std::size_t number, int size ) {
    fill_in( explosions, size, number );
}
mintercept::Explosions::Explosions( Game &game, std::size_t number, int size ) {
    fill_in( explosions, size, number );
    for ( iterator e( explosions.begin() ); e != explosions.end(); ++e )
        game( *e );
}


mintercept::Explosions::explosion_ptr mintercept::Explosions::reset( int x, int y ) {
    if ( explosions[0]->reset(x, y) ) {
        explosion_ptr head = explosions.front();
        explosions.pop_front();
        explosions.push_back(head);
        return head;
    } else
        return explosion_ptr();
}


bool mintercept::Explosions::tick() {
    for ( iterator i( explosions.begin() ); i != explosions.end(); ++i )
        (*i)->tick();
    return true;
}


void mintercept::Explosions::draw( Layer layer ) {
    for ( iterator i( explosions.begin() ); i != explosions.end(); ++i )
        (*i)->draw(layer);
}
