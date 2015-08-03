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
package it.gcatania.simpledrawing.shape;

import spock.lang.Specification
import spock.lang.Unroll
import spock.lang.Specification.*

class RectangleSpec extends Specification
{

    @Unroll
    def "rectangle has correct sides"()
    {
        Rectangle r = createRectangle(x1, y1, x2, y2)

        expect:
        new HashSet(r.sides) == new HashSet([up, right, down, left])

        where:
        x1 | y1 | x2 | y2 ||                     up |                  right |                   down |                   left
        0  |  0 |  0 |  0 || createLine(0, 0, 0, 0) | createLine(0, 0, 0, 0) | createLine(0, 0, 0, 0) | createLine(0, 0, 0, 0)
        1  |  4 |  5 |  5 || createLine(1, 4, 5, 4) | createLine(5, 4, 5, 5) | createLine(1, 5, 5, 5) | createLine(1, 4, 1, 5)
    }

    private static Line createLine(int x1, int y1, int x2, int y2)
    {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        return new Line(p1, p2);
    }

    private static Rectangle createRectangle(int x1, int y1, int x2, int y2)
    {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        return new Rectangle(p1, p2);
    }
}
