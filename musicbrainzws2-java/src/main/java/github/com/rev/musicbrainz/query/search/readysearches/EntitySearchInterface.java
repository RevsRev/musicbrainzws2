package github.com.rev.musicbrainz.query.search.readysearches;

import java.util.List;

public interface EntitySearchInterface {

    List getFullList();

    List getFirstPage();

    List getNextPage();

    boolean hasMore();
}
