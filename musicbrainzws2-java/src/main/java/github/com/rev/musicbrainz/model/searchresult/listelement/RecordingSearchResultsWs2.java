package github.com.rev.musicbrainz.model.searchresult.listelement;

import github.com.rev.musicbrainz.model.entity.listelement.RecordingListWs2;
import github.com.rev.musicbrainz.model.searchresult.RecordingResultWs2;
import github.com.rev.musicbrainz.wsxml.element.ListElement;

import java.util.ArrayList;
import java.util.List;

public class RecordingSearchResultsWs2 extends ListElement {

    protected List<RecordingResultWs2> recordingResults = new ArrayList<RecordingResultWs2>();
    private RecordingListWs2 recordingList = new RecordingListWs2();

    public List<RecordingResultWs2> getRecordingResults() {
        return recordingResults;
    }

    public RecordingListWs2 getRecordingList() {
        return recordingList;
    }

    /**
     * Convenience method to adds an track result to the list.
     * <p>
     * This will create a new <code>ArrayList</code> if {@link #recordingResults} is null.
     *
     * @param trackResult The track result to add
     */
    public void addRecordingResult(RecordingResultWs2 recordingResult) {
        if (recordingResults == null) {
            recordingResults = new ArrayList<RecordingResultWs2>();
        }
        if (recordingList == null) {
            recordingList = new RecordingListWs2();
        }
        recordingResults.add(recordingResult);
        recordingList.addRecording(recordingResult.getRecording());

        recordingList.setCount(getCount());
        recordingList.setOffset(getOffset());
    }
}
