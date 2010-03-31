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


#include "player.h"
#include <algorithm>
#include <functional>


/*
    Player
*/


mintercept::Player::Player()
: shots( 8, 35 ) {
}


void mintercept::Player::tap(int x, int y) {
    shots.reset(x, y);
}

bool mintercept::Player::tick() {
    shots.tick();
    return cities.tick();
}


void mintercept::Player::draw(Layer layer) {
    cities.draw(layer);
    shots.draw(layer);
}


/*
    Cities
*/


bool mintercept::Player::Cities::tick() {
    std::for_each( cities, cities + number_of_cities, std::mem_fun_ref(&City::tick));
    return true;
}

void mintercept::Player::Cities::draw(Layer layer) {
    std::for_each( cities, cities + number_of_cities, std::bind2nd(std::mem_fun_ref(&City::draw), layer) );
}


/*
    City
*/


bool mintercept::Player::Cities::City::tick() {
    return true;
}

void mintercept::Player::Cities::City::draw(Layer layer) {
}
