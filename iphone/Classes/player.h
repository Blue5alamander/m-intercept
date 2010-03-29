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


#pragma once


#include "explosions.h"


namespace mintercept {
    

    class Player : public Element {
        class Cities : public Element {
            static const std::size_t number_of_cities = 3;
            class City : public Element {
            public:
                bool tick();
                void draw(Layer);
            } cities[number_of_cities];
        public:
            bool tick();
            void draw(Layer);
        } cities;
        Explosions shots;
        
    public:
        Player();

        bool tap( int x, int y );

        bool tick();
        void draw(Layer);
    };


}
