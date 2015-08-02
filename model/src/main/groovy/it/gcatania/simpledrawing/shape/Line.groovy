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

class Line
{
    private Point p1;

    private Point p2;

    public Line( Point p1, Point p2 )
    {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Line()
    {
    }

    public Point getP1()
    {
        return p1;
    }

    public void setP1( Point p1 )
    {
        this.p1 = p1;
    }

    public Point getP2()
    {
        return p2;
    }

    public void setP2( Point p2 )
    {
        this.p2 = p2;
    }

    @Override
    public int hashCode()
    {
        // hashcode and equals must not be dependent on point order
        return ( 1 + Objects.hashCode( p1 ) ) * ( 1 + Objects.hashCode( p2 ) );
    }

    @Override
    public boolean equals( Object obj )
    {
        if (!(obj instanceof Line)) return false;
        Line l = obj;
        // hashcode and equals must not be dependent on point order
        return (Objects.equals(p1, l.p1) && Objects.equals( p2, l.p2)) ||
                (Objects.equals(p1, l.p2) && Objects.equals( p2, l.p1))
    }

    public String toString()
    {
        return "$p1 - $p2"
    }

    public boolean isHorizontal()
    {
        //TODO
    }

    public boolean isVertical()
    {
        //TODO
    }
}