package edu.eci.arsw.alexandria.model.Editor;

public class ByWord extends Location{
    @Override
    public int[] getBlockedRange() {
        String [] intoArray = getEditor().getText().get(getRow()).split(" ");
        int sum =0;
        for(String s: intoArray){
            if(getRow()>sum+s.length()) {
                sum += s.length();
            }else{
                return new int[]{sum,getRow(),sum+s.length(),getRow()};
            }
        }
        return new int[]{getColumn(),getRow(),getColumn()+1,getRow()+1};
    }
}
