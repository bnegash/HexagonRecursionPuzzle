public class Hexagon {

    // Variables
    int tileNum;
    int position;
    char [] tileSegs;

    //Constructor
    public Hexagon(int tileNum,String color) {
        this.tileNum = tileNum;
        this.tileSegs = color.toCharArray();
        this.position = -1;
    }

    //method that rotates the colors of each segment
    public void rotate() {
        char temp = tileSegs[0];

        //Moves segments
        System.arraycopy(tileSegs, 1, tileSegs, 0, tileSegs.length - 1);
        tileSegs[tileSegs.length -1] = temp;
    }


    public int getPosition(){
        return position;
    }

    public void setPosition(int position){
        this.position = position;
    }


    @Override
    public String toString(){
        StringBuffer segmentSpce;
        segmentSpce = new StringBuffer();

        for (char segment : tileSegs) {
            segmentSpce.append(segment).append(" ");
        }
        return "Position " + (position + 1) + ": Tile #" + tileNum + ": " + segmentSpce.toString();
    }
}