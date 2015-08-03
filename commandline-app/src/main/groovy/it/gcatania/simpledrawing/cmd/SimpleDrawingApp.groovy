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


class SimpleDrawingApp
{

    public static void main(String[] args)
    {
        Console cons = System.console();
        Canvas c = null;
        SimpleDrawingRenderer renderer = new SimpleDrawingRenderer(System.out)
        mainLoop: while(true)
        {
            String cmd = cons.readLine('enter command: ');
            if(cmd.empty) continue mainLoop;
            String[] cmdParts = cmd.split( ' ' );
            try
            {
                switch(cmdParts[0])
                {
                    case 'C':
                        int width = getArg(cmdParts, 1);
                        int height = getArg(cmdParts, 2);
                        c = new Canvas(width, height );
                        break;
                    case 'L':
                        if(c == null)
                        {
                            System.err.println('You need to create a canvas first!'); continue mainLoop;
                        }
                        c.add( new Line(
                                parsePoint(cmdParts, 1, 2),
                                parsePoint(cmdParts, 3, 4)));
                        break;
                    case 'R':
                        if(c == null)
                        {
                            System.err.println('You need to create a canvas first!'); continue mainLoop;
                        }
                        c.add( new Rectangle(
                                parsePoint(cmdParts, 1, 2),
                                parsePoint(cmdParts, 3, 4)));
                        break;
                    case 'B':
                        if(c == null)
                        {
                            System.err.println('You need to create a canvas first!'); continue mainLoop;
                        }
                        c.bucketFillFrom(parsePoint(cmdParts, 1, 2), cmdParts[3].charAt(0));
                        break;
                    case 'Q': return;
                    default:
                        System.err.println("Unrecognized command") ;
                        continue mainLoop;
                }
            } catch (RuntimeException re)
            {
                // TODO better error handling and reporting
                System.err.println(re.message);
            }
            if(c != null) renderer.draw(c);
        }
    }

    /**
     * @param args the arguments
     * @param index the argument index
     * @return an integer
     */
    private static int getArg(String[] args, int index)
    {
        String command = args[0];
        if(index >= args.length) throw new IllegalArgumentException("Missing required argument number $index for command $command")
        int i = Integer.parseInt(args[index]);
        return i;
    }

    /**
     * @param args the arguments
     * @param index the argument index
     * @return an integer, parsed and converted from one based to zero based
     */
    private static int getZeroBasedArg(String[] args, int index)
    {
        int oneBased = getArg(args, index);
        return oneBased -1;
    }

    private static Point parsePoint(String[] args, int xIndex, int yIndex)
    {
        return new Point(getZeroBasedArg(args, xIndex), getZeroBasedArg(args, yIndex))
    }
}
