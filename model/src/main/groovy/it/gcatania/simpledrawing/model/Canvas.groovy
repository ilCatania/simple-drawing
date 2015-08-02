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
        validate(l);
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
        validate(r);
        r.sides.each {add(it)}
    }

    /**
     * <p>bucket fill all points adjacent to the input point and having the same colour, changing them to the new colour.</p>
     *
     * <p>This method uses an iterative approach by keeping track of all "new" adjacent points in each iteration, and all
     * the points found in all iterations to avoid iterating indefinitely.</p>
     *
     * @param p the point to start filling from
     * @param colour the fill colour
     */
    public void bucketFillFrom(Point p, char colour)
    {
        validate(p);
        char oldColour = getColourAt(p);
        Set<Point> allPointsToFill = new HashSet<Point>();
        Set<Point> newPointsToFill = Collections.singleton(p);
        allPointsToFill.addAll(newPointsToFill);
        while(!newPointsToFill.empty)
        {
            newPointsToFill = internalGetAdjacentPointsWithSameColour(allPointsToFill, newPointsToFill, oldColour);
        }
        allPointsToFill.each { setColour(it, colour); }
    }

    /**
     * @param allPointsToFill the set of all points to be filled by the bucket fill
     * @param newPointsToFill the set of all the "new" points found by looking for adjacents to existing points in the current iteration
     * @param colour the colour of the adjacent points to find
     * @return the set of the new points found (will also be added to the existing points)
     */
    private Set<Point> internalGetAdjacentPointsWithSameColour(Set<Point> allPointsToFill, Set<Point> newPointsToFill, char colour)
    {
        Set<Point> pointsFromPreviousStep = newPointsToFill;
        newPointsToFill = new HashSet<Point>();
        pointsFromPreviousStep.each(
                { Point p ->
                    Set<Point> adjacents = adjacents(p);
                    Set<Point> withSameColour = adjacents.findAll { getColourAt(it) == colour };
                    Set<Point> newOnes = withSameColour.findAll { allPointsToFill.add(it) };
                    newPointsToFill.addAll(newOnes);
                })
        return newPointsToFill;
    }

    public char getColourAt(Point p)
    {
        validate(p);
        return pixelColours[p.x][p.y]
    }

    public boolean contains(Point p)
    {
        return p.x >= 0 && p.x < width && p.y >= 0 && p.y < height
    }

    private void setColour(Point p, char newColour)
    {
        validate(p);
        pixelColours[p.x][p.y] = newColour;
    }

    private void validate(Point p)
    {
        if(p== null) throw new IllegalArgumentException("No point provided")
        if(!contains(p)) throw new IllegalArgumentException("Point $p is outside of current canvas");
    }

    private void validate(Line l)
    {
        if(l== null) throw new IllegalArgumentException("No line provided")
        validate(l.p1);
        validate(l.p2);
    }

    private void validate(Rectangle r)
    {
        if(r== null) throw new IllegalArgumentException("No rectangle provided")
        validate(r.vertex1);
        validate(r.vertex2);
    }

    /**
     * Note: this method is private here instead of public on the {@code Point} class to keep the latter as generic as possible
     * @param p a point
     * @return the set of all adjacent points inside the canvas (empty if the input point is outside the canvas)
     */
    private Set<Point> adjacents(Point p)
    {
        if(!contains(p))
        {
            return Collections.emptySet()
        }
        Point up = new Point(p.x, p.y-1);
        Point down = new Point(p.x, p.y+1);
        Point left = new Point(p.x-1, p.y);
        Point right = new Point(p.x+1, p.y);
        List<Point> containedAdjacents = [up, down, left, right].findAll({contains(it)});
        return new HashSet<Point>(containedAdjacents)
    }
}
