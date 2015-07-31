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

class LineSpec extends Specification {

    @Unroll
    def "line #l1 equals line #l2"() {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        Point p3 = new Point(x3, y3);
        Point p4 = new Point(x4, y4);
        Line l1 = new Line(p1, p2);
        Line l2 = new Line(p3, p4);

        expect:
        l1.hashCode() == l2.hashCode() && l1.equals( l2) && l2.equals( l1)

        where:
        x1 | y1 | x2 | y2 | x3 | y3 | x4 | y4
        0 |  0 |  0 |  0 |  0 |  0 |  0 |  0
        1 |  4 |  3 |  2 |  1 |  4 |  3 |  2
        1 |  4 |  3 |  2 |  3 |  2 |  1 |  4
    }
}

