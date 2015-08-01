/*
 * Copyright 2015 Gabriele Catania <gabriele.ctn@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.gcatania.simpledrawing.model

import it.gcatania.simpledrawing.shape.Line
import it.gcatania.simpledrawing.shape.Point
import it.gcatania.simpledrawing.shape.Rectangle

class Canvas
{
    private final int width;
    private final int height;

    private final char[][] pixelColours;

    public Canvas(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.pixelColours = new char[width][height];
        //TODO fill pixels
    }

    public void add(Line l)
    {
        // TODO add impl
    }

    public void add(Rectangle r)
    {
        //TODO add impl
    }

    public void bucketFillFrom(Point p, char colour)
    {
        //TODO add impl
    }

    public char getColourAt(Point p)
    {
        // TODO add impl
        return ' ';
    }
}
