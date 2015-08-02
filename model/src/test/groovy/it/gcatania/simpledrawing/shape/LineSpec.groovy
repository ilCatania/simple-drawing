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

class LineSpec extends Specification
{

    @Unroll def"line (#x1,#y1)-(#x2,#y2) equals line (#x3,#y3)-(#x4,#y4)"()
    {
        Line l1 = createLine(x1, y1, x2, y2);
        Line l2 = createLine(x3, y3, x4, y4);

        expect:
        l1.hashCode() == l2.hashCode() && l1.equals(l2) && l2.equals(l1)

        where:
        x1 | y1 | x2 | y2 | x3 | y3 | x4 | y4
        0  |  0 |  0 |  0 |  0 |  0 |  0 |  0
        1  |  4 |  3 |  2 |  1 |  4 |  3 |  2
        1  |  4 |  3 |  2 |  3 |  2 |  1 |  4
    }

    def "line is horizontal or vertical"()
    {
        Line l = createLine(x1, y1, x2, y2)

        expect:
        l.isHorizontal() == horizontal && l.isVertical() == vertical

        where:
        x1 | y1 | x2 | y2 || horizontal | vertical
        0  |  0 |  0 |  0 ||       true |     true
        2  |  3 | 10 |  3 ||       true |    false
        1  |  4 |  1 |  7 ||      false |     true
        0  |  0 |  5 |  2 ||      false |    false
    }

    private static Line createLine(int x1, int y1, int x2, int y2)
    {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        return new Line(p1, p2);
    }
}
