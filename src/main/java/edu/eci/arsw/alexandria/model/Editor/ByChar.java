package edu.eci.arsw.alexandria.model.Editor;

import edu.eci.arsw.alexandria.model.KnowledgeBase.User;

public class ByChar extends Location{

    @Override
    public int[] getBlockedRange() {
        return new int[]{getColumn(),getRow(),getColumn()+1,getRow()+1};
    }
}
