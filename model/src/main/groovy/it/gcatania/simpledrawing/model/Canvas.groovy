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

    public static final char LINE_COLOUR = 'x';
    private final int width;
    private final int height;

    private final char[][] pixelColours;

    public Canvas(int width, int height)
    {
        this.width = width;
        this.height = height;
        pixelColours = new char[width][height];
        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++) pixelColours[x][y] = ' ';
        }
    }

    public void add(Line l)
    {
        if(l == null) throw new NullPointerException();
        if(l.horizontal)
        {
            int y = l.p1.y;
            // TODO check against canvas boundaries and report the correct error message
            int increment = Integer.signum(l.p2.x - l.p1.x)
            for(int x = l.p1.x; x <= l.p2.x; x+= increment) pixelColours[x][y] = LINE_COLOUR;
        }
        else if (l.vertical)
        {
            int x = l.p1.x;
            // TODO check against canvas boundaries and report the correct error message
            int increment = Integer.signum(l.p2.y - l.p1.y)
            for(int y = l.p1.y; y <= l.p2.y; y+= increment) pixelColours[x][y] = LINE_COLOUR;
        }
        else throw new UnsupportedOperationException('only horizontal or vertical lines are supported');
    }

    public void add(Rectangle r)
    {
        if(r == null) throw new NullPointerException();
        r.sides.each {add(it)}
    }

    public void bucketFillFrom(Point p, char colour)
    {
        //TODO add impl
    }

    public char getColourAt(Point p)
    {
        // TODO check against canvas boundaries and report the correct error message
        return pixelColours[p.x][p.y]
    }
}
