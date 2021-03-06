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
package it.gcatania.simpledrawing.shape

class Rectangle
{
    private Point vertex1;

    private Point vertex2;

    public Rectangle( Point vertex1, Point vertex2 )
    {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public Rectangle()
    {
    }

    public Point getVertex1()
    {
        return vertex1;
    }

    public void setVertex1( Point vertex1 )
    {
        this.vertex1 = vertex1;
    }

    public Point getVertex2()
    {
        return vertex2;
    }

    public void setVertex2( Point vertex2 )
    {
        this.vertex2 = vertex2;
    }

    @Override
    public int hashCode()
    {
        // hashcode and equals must not be dependent on point order, so we delegate to Line wich has the same behaviour
        return new Line(vertex1, vertex2).hashCode();
    }

    @Override
    public boolean equals( Object obj )
    {
        if (!(obj instanceof Rectangle)) return false;
        Rectangle r = obj;
        // hashcode and equals must not be dependent on point order, so we delegate to Line wich has the same behaviour
        return new Line(vertex1, vertex2).equals(new Line(r.vertex1, r.vertex2));
    }

    public String toString()
    {
        return "Rectangle [$vertex1 - $vertex2]"
    }

    /**
     * @return the rectangle sides, as a list, in "conventional" clockwise order (assuming vertex 1 is top left and vertex 2 is bottom right)
     */
    public List<Line> getSides()
    {
        // note that we never actually check that vertex 1 is top left and vertex 2 is bottom right, so the names here are only used for reference
        Point topLeft = vertex1;
        Point topRight = new Point(vertex1.x, vertex2.y);
        Point bottomRight = vertex2;
        Point bottomLeft = new Point(vertex2.x, vertex1.y);
        Line top = new Line(topLeft, topRight);
        Line right = new Line(topRight, bottomRight);
        Line bottom = new Line(bottomLeft, bottomRight);
        Line left = new Line(topLeft, bottomLeft);
        return [top, right, bottom, left]
    }
}
