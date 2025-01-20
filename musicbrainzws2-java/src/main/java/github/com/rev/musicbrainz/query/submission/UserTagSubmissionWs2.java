package github.com.rev.musicbrainz.query.submission;

import github.com.rev.musicbrainz.model.RatingsWs2;
import github.com.rev.musicbrainz.model.TagWs2;
import github.com.rev.musicbrainz.model.entity.EntityWs2;
import github.com.rev.musicbrainz.webservice.WebService;

/**
 * List of Tags to be submitted to the Web Service.
 * 
 **/
public class UserTagSubmissionWs2 extends SubmissionWs2{

   
    public UserTagSubmissionWs2(){
        super(TAG_POST);  
    }
    public UserTagSubmissionWs2(WebService ws){
        super(ws, TAG_POST);
    }
    
    @Override
    protected EntityElement setData(EntityWs2 entity, String entityType) {

        EntityElement el = new EntityElement();
        el.setEntityType(entityType);
        el.setId(entity.getId()); 
        
        for (TagWs2 tag : entity.getUserTags())
        {
            el.addTag(tag.getName());
        }
        return el;
    }
   
}
