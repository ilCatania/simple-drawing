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
package it.gcatania.simpledrawing.cmd

import it.gcatania.simpledrawing.model.Canvas
import it.gcatania.simpledrawing.shape.Line
import it.gcatania.simpledrawing.shape.Point
import it.gcatania.simpledrawing.shape.Rectangle


class SimpleDrawingRendererSpec extends ConsoleMockingSpec
{

    def 'render an empty 3x4 canvas'()
    {
        when:
        new SimpleDrawingRenderer(mockOut).draw(new Canvas(3, 4));

        then: 1*mockOut.println('-----')
        then: 4*mockOut.println('|   |')
        then: 1*mockOut.println('-----')
        0 * _
    }

    def 'render a 6x7 canvas with a vertical line'()
    {
        Canvas c = new Canvas(6, 7)
        c.add(new Line(new Point(4, 0), new Point(4, 6)))

        when:
        new SimpleDrawingRenderer(mockOut).draw(c);

        then: 1*mockOut.println('--------')
        then: 7*mockOut.println('|    x |')
        then: 1*mockOut.println('--------')
        0 * _
    }

    def 'render a complex canvas'()
    {
        Canvas c = new Canvas(20, 4);
        c.add(new Line(new Point(0, 1), new Point(5, 1)))
        c.add(new Line(new Point(5, 2), new Point(5, 3)))
        c.add(new Rectangle(new Point(15, 0), new Point(19, 2)))
        c.bucketFillFrom(new Point(9, 2), 'o' as char)

        when:
        new SimpleDrawingRenderer(mockOut).draw(c);

        then: 1*mockOut.println('----------------------');
        then: 1*mockOut.println('|oooooooooooooooxxxxx|');
        then: 1*mockOut.println('|xxxxxxooooooooox   x|');
        then: 1*mockOut.println('|     xoooooooooxxxxx|');
        then: 1*mockOut.println('|     xoooooooooooooo|');
        then: 1*mockOut.println('----------------------');
        0 * _
    }
}