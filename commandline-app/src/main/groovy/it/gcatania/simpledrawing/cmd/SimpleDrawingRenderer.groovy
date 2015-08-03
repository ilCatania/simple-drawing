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
import it.gcatania.simpledrawing.shape.Point

class SimpleDrawingRenderer
{
    private static final String HORIZONTAL_DELIMITER = '-';
    private static final String VERTICAL_DELIMITER = '|';

    private final PrintStream outputStream;

    public SimpleDrawingRenderer(PrintStream outputStream)
    {
    }

    public void draw(Canvas c)
    {
        int widthWithMargins = 2 + c.width
        outputStream.println(HORIZONTAL_DELIMITER * widthWithMargins)
        for(int y = 0 ; y < c.height; y++)
        {
            StringBuilder sb = new StringBuilder().append(VERTICAL_DELIMITER)
            for(int x = 0; x < c.width; x++)
            {
                sb.append(c.getColourAt(new Point(x, y)));
            }
            outputStream.println(sb.append(VERTICAL_DELIMITER).toString())
        }
        outputStream.println(HORIZONTAL_DELIMITER * widthWithMargins)
    }
}
