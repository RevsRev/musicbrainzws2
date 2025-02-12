package github.com.rev.musicbrainz.model;

import github.com.rev.musicbrainz.wsxml.element.ListElement;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of Relations
 */
public class RelationListWs2 extends ListElement {

    private List<RelationWs2> relations
            = new ArrayList<RelationWs2>();

    /**
     * @return the relations
     */
    public List<RelationWs2> getRelations() {
        return relations;
    }

    /**
     * @param relations the relations to set
     */
    public void setRelations(List<RelationWs2> relations) {
        this.relations = relations;
    }

    public void addRelation(RelationWs2 relation) {
        if (relations == null) {
            relations = new ArrayList<RelationWs2>();
        }

        relations.add(relation);

    }

    public void addAllRelations(List<RelationWs2> RelationList) {
        if (relations == null) {
            relations = new ArrayList<RelationWs2>();
        }

        relations.addAll(RelationList);
    }


}
