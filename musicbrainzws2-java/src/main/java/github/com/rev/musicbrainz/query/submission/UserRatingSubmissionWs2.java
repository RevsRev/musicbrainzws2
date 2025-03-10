package github.com.rev.musicbrainz.query.submission;

import github.com.rev.musicbrainz.model.RatingsWs2;
import github.com.rev.musicbrainz.model.entity.EntityWs2;
import github.com.rev.musicbrainz.webservice.WebService;

/**
 * List of Ratings to be submitted to the Web Service.
 **/
public class UserRatingSubmissionWs2 extends SubmissionWs2 {


    public UserRatingSubmissionWs2() {

        super(RATING_POST);

    }

    public UserRatingSubmissionWs2(WebService ws) {
        super(ws, RATING_POST);
    }

    @Override
    protected EntityElement setData(EntityWs2 entity, String entityType) {

        EntityElement el = new EntityElement();
        el.setEntityType(entityType);
        el.setId(entity.getId());

        RatingsWs2 rating = entity.getUserRating();
        //rating must be postet in percent ratio.
        el.setUserRating(Math.round(rating.getAverageRating() / 5 * 100));

        return el;
    }

}
