package edu.eci.arsw.alexandria.model.Editor;


public class ByLine extends Location{

    @Override
    public int[] getBlockedRange() {
        int last = getEditor().getText().getContent().get(getRow()).length();
        return new int[]{0,getRow(),last,getRow()};
    }
}
