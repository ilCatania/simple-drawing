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
import spock.lang.Specification
import spock.lang.Unroll

class CanvasSpec extends Specification
{
    /*
     * TODO missing tests:
     * attempt at creating a diagonal line
     * attempt at getting a colour for a point outside the canvas
     * attempt at adding a line or a rectangle for a point outside the canvas
     * attempt at bucket filling outside the canvas
     * repeated bucket fills
     * bucket fill with colour 'x' followed by other bucket fill with different colour (should reset the canvas to the new colour)
     */

    @Unroll
    def "empty canvas contains a space in coordinates #x, #y"()
    {
        Canvas c = new Canvas(2, 3)

        expect:
        c.getColourAt(new Point(x, y)) == ' '

        where:
        [x, y]<< [0..<2, 0..<3].combinations()
    }

    @Unroll
    def "canvas contains an X at (#x, #y) after adding a line"()
    {
        Canvas c = new Canvas(5, 5)
        c.add(new Line(new Point(3, 1), new Point(3, 3)))

        expect:
        c.getColourAt(new Point(x, y)) == 'x'

        where:
        [x, y]<< [[3], 1..3].combinations()
    }

    def "canvas contains Xes after adding a rectangle"()
    {
        Canvas c = new Canvas(5, 5)
        c.add(new Rectangle(new Point(1, 1), new Point(3, 3)))

        expect:
        c.getColourAt(new Point(x, y)) == 'x'

        where:
        [x, y]<< [1..3, 1..3].combinations() - [[2, 2]]
    }

    def "canvas is bucket filled"()
    {
        Canvas c = new Canvas(5, 3)
        c.bucketFillFrom(new Point(2, 1), 'a' as char)
        expect:
        c.getColourAt(new Point(x, y)) == 'a'

        where:
        [x, y]<< [0..<5, 0..<3].combinations()
    }

    def 'canvas works correctly when using lines, rectangles and bucket fills togheter'()
    {
        Canvas c = new Canvas(20, 4);
        c.add(new Line(new Point(0, 1), new Point(5, 1)))
        c.add(new Line(new Point(5, 2), new Point(5, 3)))
        c.add(new Rectangle(new Point(15, 0), new Point(19, 2)))
        c.bucketFillFrom(new Point(9, 2), 'o' as char)

        expect: c.getColourAt(new Point(x, y)) == col

        where:
        [x, y, col]<< readCanvasColoursFromFile('complex.txt')
    }

    @Unroll
    def "canvas of size #w, #h contains or does not contain point (#x, #y)"()
    {
        expect:
        new Canvas(w, h).contains(new Point(x, y)) == contained

        where:
        w | h | x | y || contained
        0 | 0 | 0 | 0 || false
        1 | 1 | 0 | 0 || true
        1 | 1 | 1 | 1 || false
        3 | 4 | 2 | 2 || true
        3 | 4 | 2 | 4 || false
        3 | 4 | 3 | 2 || false
    }

    private static List readCanvasColoursFromFile(String name)
    {
        List colours = [];
        CanvasSpec.class.getResource("/$name").text.eachLine(
                { String line, int y ->
                    for(int x = 0; x < line.length(); x++)
                    {
                        colours.add([x, y, line.charAt(x)])
                    }
                })
        return colours;
    }
}