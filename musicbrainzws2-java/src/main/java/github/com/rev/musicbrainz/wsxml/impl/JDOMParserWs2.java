package github.com.rev.musicbrainz.wsxml.impl;

import github.com.rev.musicbrainz.DomainsWs2;
import github.com.rev.musicbrainz.model.AliasWs2;
import github.com.rev.musicbrainz.model.AnnotationWs2;
import github.com.rev.musicbrainz.model.ArtistCreditWs2;
import github.com.rev.musicbrainz.model.CoverArtArchiveWs2;
import github.com.rev.musicbrainz.model.IsrcWs2;
import github.com.rev.musicbrainz.model.LabelInfoListWs2;
import github.com.rev.musicbrainz.model.LabelInfoWs2;
import github.com.rev.musicbrainz.model.LifeSpanWs2;
import github.com.rev.musicbrainz.model.MediumListWs2;
import github.com.rev.musicbrainz.model.MediumWs2;
import github.com.rev.musicbrainz.model.NameCreditWs2;
import github.com.rev.musicbrainz.model.PuidWs2;
import github.com.rev.musicbrainz.model.RatingsWs2;
import github.com.rev.musicbrainz.model.RelationListWs2;
import github.com.rev.musicbrainz.model.RelationWs2;
import github.com.rev.musicbrainz.model.ReleaseEventListWs2;
import github.com.rev.musicbrainz.model.ReleaseEventWs2;
import github.com.rev.musicbrainz.model.TagWs2;
import github.com.rev.musicbrainz.model.TrackListWs2;
import github.com.rev.musicbrainz.model.TrackWs2;
import github.com.rev.musicbrainz.model.WorkAttributeWs2;
import github.com.rev.musicbrainz.model.entity.AreaWs2;
import github.com.rev.musicbrainz.model.entity.ArtistWs2;
import github.com.rev.musicbrainz.model.entity.CollectionWs2;
import github.com.rev.musicbrainz.model.entity.DiscWs2;
import github.com.rev.musicbrainz.model.entity.EntityWs2;
import github.com.rev.musicbrainz.model.entity.InstrumentWs2;
import github.com.rev.musicbrainz.model.entity.LabelWs2;
import github.com.rev.musicbrainz.model.entity.PlaceWs2;
import github.com.rev.musicbrainz.model.entity.RecordingWs2;
import github.com.rev.musicbrainz.model.entity.ReleaseGroupWs2;
import github.com.rev.musicbrainz.model.entity.ReleaseWs2;
import github.com.rev.musicbrainz.model.entity.SeriesWs2;
import github.com.rev.musicbrainz.model.entity.WorkWs2;
import github.com.rev.musicbrainz.model.entity.listelement.AreaListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.ArtistListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.DiscListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.InstrumentListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.LabelListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.PlaceListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.RecordingListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.ReleaseGroupListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.ReleaseListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.SeriesListWs2;
import github.com.rev.musicbrainz.model.entity.listelement.WorkListWs2;
import github.com.rev.musicbrainz.model.searchresult.AnnotationResultWs2;
import github.com.rev.musicbrainz.model.searchresult.AreaResultWs2;
import github.com.rev.musicbrainz.model.searchresult.ArtistResultWs2;
import github.com.rev.musicbrainz.model.searchresult.CollectionResultWs2;
import github.com.rev.musicbrainz.model.searchresult.InstrumentResultWs2;
import github.com.rev.musicbrainz.model.searchresult.LabelResultWs2;
import github.com.rev.musicbrainz.model.searchresult.PlaceResultWs2;
import github.com.rev.musicbrainz.model.searchresult.RecordingResultWs2;
import github.com.rev.musicbrainz.model.searchresult.ReleaseGroupResultWs2;
import github.com.rev.musicbrainz.model.searchresult.ReleaseResultWs2;
import github.com.rev.musicbrainz.model.searchresult.SeriesResultWs2;
import github.com.rev.musicbrainz.model.searchresult.WorkResultWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.AnnotationSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.AreaSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.ArtistSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.CollectionSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.InstrumentSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.LabelSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.PlaceSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.RecordingSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.ReleaseGroupSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.ReleaseSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.SeriesSearchResultsWs2;
import github.com.rev.musicbrainz.model.searchresult.listelement.WorkSearchResultsWs2;
import github.com.rev.musicbrainz.utils.MbUtils;
import github.com.rev.musicbrainz.wsxml.MbXMLException;
import github.com.rev.musicbrainz.wsxml.MbXMLParseException;
import github.com.rev.musicbrainz.wsxml.MbXmlParser;
import github.com.rev.musicbrainz.wsxml.element.ListElement;
import github.com.rev.musicbrainz.wsxml.element.Metadata;
import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <p>A parser for the Music Metadata XML format.</p>
 *
 * <p>This parser supports all basic features and extensions defined by
 * MusicBrainz, including unlimited document nesting. By default it
 * reads an XML document from a stream and returns an object tree
 * representing the document using the flexible {@link Metadata}
 * class.</p>
 *
 * <p>The implementation tries to be as permissive as possible. Invalid
 * contents are skipped, but documents have to be well-formed and using
 * the correct namespace. In case of unrecoverable errors, a
 * {@link MbXMLParseException} exception is raised.</p>
 */
public class JDOMParserWs2 extends DomainsWs2 implements MbXmlParser {

    private static final Logger log = Logger.getLogger(JDOMParserWs2.class.getName());

    /**
     * Default constructor
     */
    public JDOMParserWs2() {

    }

    /* (non-Javadoc)
     * @see github.com.rev.musicbrainz.wsxml.MbXmlParser#parse(java.io.InputStream)
     */
    public Metadata parse(InputStream inputStream) throws MbXMLException {
        SAXBuilder builder = new SAXBuilder();
        try {
            Document doc = builder.build(inputStream);

            Element root = doc.getRootElement();
            if ((root == null) || (!NS_MMD_2.equals(root.getNamespace())) ||
                    !METADATA.equals(root.getName())) {
                throw new MbXMLParseException("No root element with the name metadata in " + NS_MMD_2 + " found!");
            }

            return createMetadata(root);
        } catch (Exception e) {
            throw new MbXMLParseException("The xml could not be read!", e);
        }
    }

    protected Metadata createMetadata(Element elem) {
        Metadata md = new Metadata();

        Iterator itr = elem.getChildren().iterator();
        Element node;
        while (itr.hasNext()) {
            node = (Element) itr.next();

            if (LABEL.equals(node.getName())) {
                md.setLabelWs2(createLabel(node));
            } else if (ARTIST.equals(node.getName())) {
                md.setArtistWs2(createArtist(node));
            } else if (RELEASEGROUP.equals(node.getName())) {
                md.setReleaseGroupWs2(createReleaseGroup(node));
            } else if (RELEASE.equals(node.getName())) {
                md.setReleaseWs2(createRelease(node));
            } else if (WORK.equals(node.getName())) {
                md.setWorkWs2(createWork(node));
            } else if (RECORDING.equals(node.getName())) {
                md.setRecordingWs2(createRecording(node));
            } else if (AREA.equals(node.getName())) {
                md.setAreaWs2(createArea(node));
            } else if (PLACE.equals(node.getName())) {
                md.setPlaceWs2(createPlace(node));
            } else if (INSTRUMENT.equals(node.getName())) {
                md.setInstrumentWs2(createInstrument(node));
            } else if (SERIES.equals(node.getName())) {
                md.setSeriesWs2(createSeries(node));
            } else if (ANNOTATION.equals(node.getName())) {
                md.setAnnotationWs2(createAnnotation(node));
            } else if (COLLECTION.equals(node.getName())) {
                md.setCollectionWs2(createCollection(node));
            } else if (DISC.equals(node.getName())) {
                md.setDiscWs2(createDisc(node));
            } else if (PUID.equals(node.getName())) {
                md.setPuidWs2(createPuid(node));
            } else if (MESSAGE.equals(node.getName())) {
                md.setMessage(createMessage(node));
            } else if (LABELLIST.equals(node.getName())) {
                addLabelResults(node, md.getLabelResultsWs2());
            } else if (ARTISTLIST.equals(node.getName())) {
                addArtistResults(node, md.getArtistResultsWs2());
            } else if (RELEASEGROUPLIST.equals(node.getName())) {
                addReleaseGroupResults(node, md.getReleaseGroupResultsWs2());
            } else if (RELEASELIST.equals(node.getName())) {
                addReleaseResults(node, md.getReleaseResultsWs2());
            } else if (RECORDINGLIST.equals(node.getName())) {
                addRecordingResults(node, md.getRecordingResultsWs2());
            } else if (WORKLIST.equals(node.getName())) {
                addWorkResults(node, md.getWorkResultsWs2());
            } else if (AREALIST.equals(node.getName())) {
                addAreaResults(node, md.getAreaResultsWs2());
            } else if (PLACELIST.equals(node.getName())) {
                addPlaceResults(node, md.getPlaceResultsWs2());
            } else if (INSTRUMENTLIST.equals(node.getName())) {
                addInstrumentResults(node, md.getInstrumentResultsWs2());
            } else if (SERIESLIST.equals(node.getName())) {
                addSeriesResults(node, md.getSeriesResultsWs2());
            } else if (COLLECTIONLIST.equals(node.getName())) {
                addCollectionResults(node, md.getCollectionResultsWs2());
            } else if (ANNOTATIONLIST.equals(node.getName())) {
                addAnnotationResults(node, md.getAnnotationResultsWs2());
            } else {
                log.warning("Unrecognised metadata element: " + node.getName());
            }
        }
        return md;
    }

    protected LabelWs2 createLabel(Element node) {
        LabelWs2 label = new LabelWs2();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(ID)) {
                label.setIdUri(MbUtils.convertIdToURI(attribute.getValue(), LABEL));
            } else if (attribute.getName().equals(TYPE)) {
                label.setTypeUri(MbUtils.convertTypeToURI(attribute.getValue(), NS_MMD_2_PREFIX));
            } else if (attribute.getName().equals(SCORE)) {
                //ignore.
            } else {
                log.warning("Unrecognised Label attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();

            if (NAME.equals(child.getName())) {
                label.setName(child.getText());
            } else if (SORTNAME.equals(child.getName())) {
                label.setSortName(child.getText());
            } else if (LABELCODE.equals(child.getName())) {
                label.setLabelCode(child.getText());
            } else if (IPI.equals(child.getName())) {
                label.setIpi(child.getText());
            }
            //2014-09-20 still not usable
            //else if (ANNOTATION.equals(child.getName())) {
            //     artist.setAnnotation(createAnnotation(child));
            //}
            else if (DISAMBIGUATION.equals(child.getName())) {
                label.setDisambiguation(child.getText());
            } else if (COUNTRY.equals(child.getName())) {
                label.setCountry(child.getText());
            } else if (AREA.equals(child.getName())) {
                label.setArea(createArea(child));
            } else if (LIFESPAN.equals(child.getName())) {
                label.setLifeSpan(createLifeSpan(child));
            } else if (ALIASLIST.equals(child.getName())) {
                addAliases(child, label);
            } else if (RELEASELIST.equals(child.getName())) {
                addReleasesToList(child, label.getReleaseList());
            } else if (RELATIONLIST.equals(child.getName())) {
                addRelationsToEntity(child, label.getRelationList());
            } else if (TAGLIST.equals(child.getName())) {
                addTags(child, label);
            } else if (USERTAGLIST.equals(child.getName())) {
                addUserTags(child, label);
            } else if (RATING.equals(child.getName())) {
                label.setRating(createRating(child));
            } else if (USERRATING.equals(child.getName())) {
                label.setUserRating(createUserRating(child));
            } else {
                log.warning("Unrecognised Label element: " + child.getName());
            }
        }
        return label;
    }

    protected ArtistWs2 createArtist(Element node) {

        ArtistWs2 artist = new ArtistWs2();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(ID)) {
                artist.setIdUri(MbUtils.convertIdToURI(attribute.getValue(), ARTIST));
            } else if (attribute.getName().equals(TYPE)) {
                artist.setTypeUri(MbUtils.convertTypeToURI(attribute.getValue(), NS_MMD_2_PREFIX));
            } else if (attribute.getName().equals(SCORE)) {
                //ignore.
            } else {
                log.warning("Unrecognised Artist attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();

            if (NAME.equals(child.getName())) {
                artist.setName(child.getText());
            } else if (SORTNAME.equals(child.getName())) {
                artist.setSortName(child.getText());
            } else if (GENDER.equals(child.getName())) {
                artist.setGender(child.getText());
            } else if (COUNTRY.equals(child.getName())) {
                artist.setCountry(child.getText());
            } else if (AREA.equals(child.getName())) {
                artist.setArea(createArea(child));
            } else if (BEGINAREA.equals(child.getName())) {
                artist.setBeginArea(createArea(child));
            } else if (ENDAREA.equals(child.getName())) {
                artist.setEndArea(createArea(child));
            }
            //2014-09-20 still not usable
            //else if (ANNOTATION.equals(child.getName())) {
            //     artist.setAnnotation(createAnnotation(child));
            //}
            else if (DISAMBIGUATION.equals(child.getName())) {
                artist.setDisambiguation(child.getText());
            } else if (IPI.equals(child.getName())) {
                artist.setIpi(child.getText());
            } else if (IPILIST.equals(child.getName())) {
                artist.setIpiList(getArrayElementsAsList(child));
            } else if (LIFESPAN.equals(child.getName())) {
                artist.setLifeSpan(createLifeSpan(child));
            } else if (ALIASLIST.equals(child.getName())) {
                addAliases(child, artist);
            } else if (RECORDINGLIST.equals(child.getName())) {
                addRecordingToList(child, artist.getRecordingList());
            } else if (RELEASELIST.equals(child.getName())) {
                addReleasesToList(child, artist.getReleaseList());
            } else if (RELEASEGROUPLIST.equals(child.getName())) {
                addReleaseGroupsToList(child, artist.getReleaseGroupList());
            }
            //2014-09-20 still in mmd but not allowed
            //else if (LABELLIST.equals(child.getName())) {
            // addLabelsToList(child, artist.getLabelList());
            //}
            else if (WORKLIST.equals(child.getName())) {
                addWorkToList(child, artist.getWorkList());
            } else if (RELATIONLIST.equals(child.getName())) {
                addRelationsToEntity(child, artist.getRelationList());
            } else if (TAGLIST.equals(child.getName())) {
                addTags(child, artist);
            } else if (USERTAGLIST.equals(child.getName())) {
                addUserTags(child, artist);
            } else if (RATING.equals(child.getName())) {
                artist.setRating(createRating(child));
            } else if (USERRATING.equals(child.getName())) {
                artist.setUserRating(createUserRating(child));
            }
            // 2014/09/20 Not in mmd but still returned.
            else if (ISNILIST.equals(child.getName())) {
                artist.setIsniList(getArrayElementsAsList(child));
            } else {
                log.warning("Unrecognised Artist element: " + child.getName());
            }
        }
        return artist;
    }

    protected ReleaseGroupWs2 createReleaseGroup(Element node) {

        if (node.getAttributeValue(ID) == null) {
            return null;
        }

        ReleaseGroupWs2 releaseGroup = new ReleaseGroupWs2();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(ID)) {
                releaseGroup.setIdUri(
                        MbUtils.convertIdToURI(attribute.getValue(),
                                RELEASEGROUP));
            } else if (attribute.getName().equals(TYPE)) {
                releaseGroup.setTypeUri(
                        MbUtils.convertTypeToURI(attribute.getValue(),
                                NS_MMD_2_PREFIX));
            } else if (attribute.getName().equals(SCORE)) {
                //ignore.
            } else {
                log.warning("Unrecognised Release Group attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (TITLE.equals(child.getName())) {
                releaseGroup.setTitle(child.getText());
            }
            //2014-09-20 still not usable
            //else if (ANNOTATION.equals(child.getName())) {
            //     artist.setAnnotation(createAnnotation(child));
            //}
            else if (DISAMBIGUATION.equals(child.getName())) {
                releaseGroup.setDisambiguation(child.getText());
            } else if (FIRSTRELEASEDATE.equals(child.getName())) {
                releaseGroup.setFirstReleaseDateStr(child.getText());
            } else if (PRIMARYTYPE.equals(child.getName())) {
                releaseGroup.setPrimaryType(child.getText());
            } else if (SECONDARYTYPELIST.equals(child.getName())) {
                releaseGroup.setSecondaryTypes(getArrayElementsAsList(child));
            } else if (ARTISTCREDIT.equals(child.getName())) {
                releaseGroup.setArtistCredit(createArtistCredit(child));
            } else if (RELEASELIST.equals(child.getName())) {
                addReleasesToList(child, releaseGroup.getReleaseList());
            } else if (RELATIONLIST.equals(child.getName())) {
                addRelationsToEntity(child, releaseGroup.getRelationList());
            } else if (TAGLIST.equals(child.getName())) {
                addTags(child, releaseGroup);
            } else if (USERTAGLIST.equals(child.getName())) {
                addUserTags(child, releaseGroup);
            } else if (RATING.equals(child.getName())) {
                releaseGroup.setRating(createRating(child));
            } else if (USERRATING.equals(child.getName())) {
                releaseGroup.setUserRating(createUserRating(child));
            } else {
                log.warning("Unrecognised Release Group element: " + child.getName());
            }
        }
        return releaseGroup;
    }

    protected ReleaseWs2 createRelease(Element node) {

        ReleaseWs2 release = new ReleaseWs2();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(ID)) {
                release.setIdUri(MbUtils.convertIdToURI(attribute.getValue(),
                        RELEASE));
            } else if (attribute.getName().equals(SCORE)) {
                //ignore.
            } else {
                log.warning("Unrecognised Release attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (TITLE.equals(child.getName())) {
                release.setTitle(child.getText());
            } else if (STATUS.equals(child.getName())) {
                release.setStatus(child.getText());
            } else if (QUALITY.equals(child.getName())) {
                release.setQualityStr(child.getText());
                release.setQuality(MbUtils.convertTypeToURI(
                        child.getText(),
                        NS_MMD_2_PREFIX));
            }
            //2014-09-20 still not usable
            //else if (ANNOTATION.equals(child.getName())) {
            //     artist.setAnnotation(createAnnotation(child));
            //}
            else if (DISAMBIGUATION.equals(child.getName())) {
                release.setDisambiguation(child.getText());
            } else if (PACKAGING.equals(child.getName())) {
                release.setPackaging(child.getText());
            } else if (TEXTREPRESENTATION.equals(child.getName())) {

                Iterator itr2 = child.getChildren().iterator();
                Element el;
                while (itr2.hasNext()) {
                    el = (Element) itr2.next();
                    if (LANGUAGE.equals(el.getName())) {
                        release.setTextLanguage(getText(el.getText(), languagePattern, el.getText()));
                    } else if (SCRIPT.equals(el.getName())) {
                        release.setTextScript(getText(el.getText(), scriptPattern, el.getText()));
                    }
                }
            } else if (ARTISTCREDIT.equals(child.getName())) {
                release.setArtistCredit(createArtistCredit(child));
            } else if (RELEASEGROUP.equals(child.getName())) {
                release.setReleaseGroup(createReleaseGroup(child));
            } else if (DATE.equals(child.getName())) {
                release.setDateStr(child.getText());
            } else if (COUNTRY.equals(child.getName())) {
                release.setCountryId(getText(child.getText().toUpperCase(), countryPattern, null));
            } else if (RELEASEEVENTLIST.equals(child.getName())) {
                ReleaseEventListWs2 evl = createReleaseEventList(child);
                release.setEventList(evl);
            } else if (BARCODE.equals(child.getName())) {
                release.setBarcode(child.getText());
            } else if (ASIN.equals(child.getName())) {
                release.setAsin(child.getText());
            } else if (COVERARTARCHIVE.equals(child.getName())) {

                CoverArtArchiveWs2 caa = createCoverArtArchive(child, release.getId());
                release.setCoverArtArchive(caa);
            } else if (LABELINFOLIST.equals(child.getName())) {
                release.setLabelInfoList(createLabelInfoList(child));
            } else if (MEDIUMLIST.equals(child.getName())) {
                release.setMediumList(createMediumList(child));
            } else if (RELATIONLIST.equals(child.getName())) {
                addRelationsToEntity(child, release.getRelationList());
            } else if (TAGLIST.equals(child.getName())) {
                // not in MB at the moment.
                addTags(child, release);
            } else if (USERTAGLIST.equals(child.getName())) {
                // not in MB at the moment.
                addUserTags(child, release);
            }
            /* 2014-09-20 Not handled this way.
            else if (COLLECTIONLIST.equals(child.getName())) {
                CollectionListWs2 cols = createCollectionList(child);
                release.setCollection(cols);
        }*/
            // 2014-09-20 Not in the MMD, admitted in ws2 but no data returned.
            else if (RATING.equals(child.getName())) {
                // not in MB at the moment.
                release.setRating(createRating(child));
            } else if (USERRATING.equals(child.getName())) {
                release.setUserRating(createUserRating(child));
            }
            //
            else {
                log.log(Level.WARNING, "Unrecognised Release element: {0}", child.getName());
            }
        }
        return release;
    }

    protected RecordingWs2 createRecording(Element node) {

        RecordingWs2 recording = new RecordingWs2();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(ID)) {
                recording.setIdUri(MbUtils.convertIdToURI(attribute.getValue(), RECORDING));
            } else if (attribute.getName().equals(SCORE)) {
                //ignore.
            } else {
                log.warning("Unrecognised Recording attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;

        while (itr.hasNext()) {

            child = (Element) itr.next();
            if (TITLE.equals(child.getName())) {
                recording.setTitle(child.getText());
            } else if (LENGTH.equals(child.getName())) {
                String d = child.getValue();
                if (d != null) {
                    try {
                        Long l = Long.parseLong(d);
                        recording.setDurationInMillis(l);
                    } catch (NumberFormatException e) {
                        log.warning("Illegal duration value!");
                    }
                }
            }
            //2014-09-20 still not usable
            //else if (ANNOTATION.equals(child.getName())) {
            //     artist.setAnnotation(createAnnotation(child));
            //}
            else if (DISAMBIGUATION.equals(child.getName())) {
                recording.setDisambiguation(child.getText());
            }
            // 2014-09-20.
            else if (VIDEO.equals(child.getName())) {
                recording.setVideo(isVideo(child));
            }
            //
            else if (ARTISTCREDIT.equals(child.getName())) {
                recording.setArtistCredit(createArtistCredit(child));
            } else if (RELEASELIST.equals(child.getName())) {
                addReleasesToList(child, recording.getReleaseList());
            } else if (PUIDLIST.equals(child.getName())) {
                addPuidsToRecording(child, recording);
            } else if (ISRCLIST.equals(child.getName())) {
                addIsrcsToRecording(child, recording);
            } else if (RELATIONLIST.equals(child.getName())) {
                addRelationsToEntity(child, recording.getRelationList());
            } else if (TAGLIST.equals(child.getName())) {
                addTags(child, recording);
            } else if (USERTAGLIST.equals(child.getName())) {
                addUserTags(child, recording);
            } else if (RATING.equals(child.getName())) {
                recording.setRating(createRating(child));
            } else if (USERRATING.equals(child.getName())) {
                recording.setUserRating(createUserRating(child));
            } else {
                log.warning("Unrecognised Recording element: " + child.getName());
            }
        }
        return recording;
    }

    private boolean isVideo(Element node) {

        boolean video = false;

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();
            log.warning("Unrecognised Video attribute: " + attribute.getName());
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (VIDEO.equals(child.getName())) {
                video = getBoolean(child.getText());
            } else {
                log.warning("Unrecognised Video element: " + child.getName());
            }
        }
        return video;
    }

    private void addPuidsToRecording(Element node,
                                     RecordingWs2 recording) {
        if (recording == null) {
            throw new NullPointerException();
        }

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (PUID.equals(child.getName())) {
                recording.addPuid(createPuid(child));
            }
        }
    }

    private void addIsrcsToRecording(Element node,
                                     RecordingWs2 recording) {
        if (recording == null) {
            throw new NullPointerException();
        }

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (ISRC.equals(child.getName())) {
                recording.addIsrc(createIsrc(child));
            }
        }
    }

    private IsrcWs2 createIsrc(Element node) {
        IsrcWs2 isrc = new IsrcWs2();
        //isrc.setId(node.getAttributeValue(ID));

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {

            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(ID)) {
                isrc.setId(attribute.getValue());
            } else {
                log.warning("Unrecognised Isrc attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (RECORDINGLIST.equals(child.getName())) {
                addRecordingToList(child, isrc.getRecordingList());
            } else {
                log.warning("Unrecognised Isrc element: " + child.getName());
            }
        }
        return isrc;
    }

    protected WorkWs2 createWork(Element node) {

        WorkWs2 work = new WorkWs2();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {

            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(ID)) {
                work.setIdUri(MbUtils.convertIdToURI(attribute.getValue(), WORK));
            } else if (attribute.getName().equals(TYPE)) {
                work.setTypeUri(MbUtils.convertTypeToURI(attribute.getValue(), NS_MMD_2_PREFIX));
            } else if (attribute.getName().equals(SCORE)) {
                //ignore.
            } else {
                log.warning("Unrecognised Work attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (TITLE.equals(child.getName())) {
                work.setTitle(child.getText());
            } else if (LANGUAGE.equals(child.getName())) {
                work.setTextLanguage(getText(child.getText(), languagePattern, child.getText()));
            } else if (ARTISTCREDIT.equals(child.getName())) {
                work.setArtistCredit(createArtistCredit(child));
            } else if (ISWC.equals(child.getName())) {
                work.setIswc(child.getText());
            } else if (ISWCLIST.equals(child.getName())) {
                work.setIswcList(getArrayElementsAsList(child));
            } else if (WORKATTRIBUTELIST.equals(child.getName())) {
                work.setAttributes(createWorkAttributeList(child));
            }
            //2014-09-20 still not usable
            //else if (ANNOTATION.equals(child.getName())) {
            //     artist.setAnnotation(createAnnotation(child));
            //}
            else if (DISAMBIGUATION.equals(child.getName())) {
                work.setDisambiguation(child.getText());
            } else if (ALIASLIST.equals(child.getName())) {
                addAliases(child, work);
            } else if (RELATIONLIST.equals(child.getName())) {
                addRelationsToEntity(child, work.getRelationList());
            } else if (TAGLIST.equals(child.getName())) {
                addTags(child, work);
            } else if (USERTAGLIST.equals(child.getName())) {
                addUserTags(child, work);
            } else if (RATING.equals(child.getName())) {
                work.setRating(createRating(child));
            } else if (USERRATING.equals(child.getName())) {
                work.setUserRating(createUserRating(child));
            } else {
                log.warning("Unrecognised Work element: " + child.getName());
            }
        }
        return work;
    }

    private List<WorkAttributeWs2> createWorkAttributeList(Element node) {
        List<WorkAttributeWs2> workAttributes
                = new ArrayList<WorkAttributeWs2>();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();
            log.warning("Unrecognised Work Attribute List attribute: " + attribute.getName());
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {

            child = (Element) itr.next();

            if (ATTRIBUTE.equals(child.getName())) {
                WorkAttributeWs2 wa = createWorkAttribute(child);
                workAttributes.add(wa);
            } else {
                log.warning("Unrecognised Work Attribute List element: " + child.getName());
            }
        }
        return workAttributes;
    }

    private WorkAttributeWs2 createWorkAttribute(Element node) {

        String type = "";
        String value = "";

        Iterator itrWorkAttribute = node.getAttributes().iterator();
        while (itrWorkAttribute.hasNext()) {

            Attribute attribute = (Attribute) itrWorkAttribute.next();
            if (attribute.getName().equals(TYPE)) {
                type = attribute.getValue();
            } else {
                log.warning("Unrecognised Work Attribute attribute: " + attribute.getName());
            }
        }
        itrWorkAttribute = node.getChildren().iterator();
        Element elWorkAttribute;
        while (itrWorkAttribute.hasNext()) {
            elWorkAttribute = (Element) itrWorkAttribute.next();
            log.warning("Unrecognised Work Attribute element: " + elWorkAttribute.getName());
        }
        value = node.getText();
        return new WorkAttributeWs2(type, value);
    }
    // 2014-09-20: Added support for new entities.

    protected AreaWs2 createArea(Element node) {
        AreaWs2 area = new AreaWs2();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {

            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(ID)) {
                area.setIdUri(MbUtils.convertIdToURI(attribute.getValue(), AREA));
            } else if (attribute.getName().equals(TYPE)) {
                area.setTypeUri(attribute.getValue());
            } else if (attribute.getName().equals(SCORE)) {
                //Ignore.
            } else {
                log.warning("Unrecognised Area attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (NAME.equals(child.getName())) {
                area.setName(child.getText());
            } else if (SORTNAME.equals(child.getName())) {
                area.setName(child.getText());
            } else if (DISAMBIGUATION.equals(child.getName())) {
                area.setDisambiguation(child.getText());
            } else if (ISO31661CODELIST.equals(child.getName())) {
                area.setIso_3166_1_codes(getArrayElementsAsList(child));
            } else if (ISO31662CODELIST.equals(child.getName())) {
                area.setIso_3166_2_codes(getArrayElementsAsList(child));
            } else if (ISO31663CODELIST.equals(child.getName())) {
                area.setIso_3166_3_codes(getArrayElementsAsList(child));
            }
            //2014-09-20 still not usable
            //else if (ANNOTATION.equals(child.getName())) {
            //     artist.setAnnotation(createAnnotation(child));
            //}
            else if (LIFESPAN.equals(child.getName())) {
                area.setLifeSpan(createLifeSpan(child));
            } else if (ALIASLIST.equals(child.getName())) {
                addAliases(child, area);
            } else if (RELATIONLIST.equals(child.getName())) {
                addRelationsToEntity(child, area.getRelationList());
            } else {
                log.warning("Unrecognised Area element: " + child.getName());
            }
        }
        return area;
    }

    protected PlaceWs2 createPlace(Element node) {
        PlaceWs2 place = new PlaceWs2();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {

            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(ID)) {
                place.setIdUri(MbUtils.convertIdToURI(attribute.getValue(), PLACE));
            } else if (attribute.getName().equals(TYPE)) {
                place.setTypeUri(attribute.getValue());
            } else if (attribute.getName().equals(SCORE)) {
                //Ignore.
            } else {
                log.warning("Unrecognised Place attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (NAME.equals(child.getName())) {
                place.setName(child.getText());
            } else if (DISAMBIGUATION.equals(child.getName())) {
                place.setDisambiguation(child.getText());
            } else if (ADDRESS.equals(child.getName())) {
                place.setAddress(child.getText());
            } else if (COORDINATES.equals(child.getName())) {

                Iterator itr2 = child.getChildren().iterator();
                Element el;
                while (itr2.hasNext()) {
                    el = (Element) itr2.next();
                    if (LATITUDE.equals(el.getName())) {
                        place.setLatitude(el.getText());
                    } else if (LONGITUDE.equals(el.getName())) {
                        place.setLongitude(el.getText());
                    } else {
                        log.warning("Unrecognised Coordinates element: " + el.getName());
                    }
                }
            }
            //2014-09-20 still not usable
            //else if (ANNOTATION.equals(child.getName())) {
            //     artist.setAnnotation(createAnnotation(child));
            //}
            else if (AREA.equals(child.getName())) {
                place.setArea(createArea(child));
            } else if (LIFESPAN.equals(child.getName())) {
                place.setLifespan(createLifeSpan(child));
            } else if (ALIASLIST.equals(child.getName())) {
                addAliases(child, place);
            } else if (RELATIONLIST.equals(child.getName())) {
                addRelationsToEntity(child, place.getRelationList());
            } else if (TAGLIST.equals(child.getName())) {
                addTags(child, place);
            } else if (USERTAGLIST.equals(child.getName())) {
                addUserTags(child, place);
            } else {
                log.warning("Unrecognised Place element: " + child.getName());
            }
        }
        return place;
    }

    protected InstrumentWs2 createInstrument(Element node) {
        InstrumentWs2 instrument = new InstrumentWs2();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {

            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(ID)) {
                instrument.setIdUri(MbUtils.convertIdToURI(attribute.getValue(), PLACE));
            } else if (attribute.getName().equals(TYPE)) {
                instrument.setTypeUri(attribute.getValue());
            } else if (attribute.getName().equals(SCORE)) {
                //Ignore.
            } else {
                log.warning("Unrecognised Instrument attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (NAME.equals(child.getName())) {
                instrument.setName(child.getText());
            } else if (DISAMBIGUATION.equals(child.getName())) {
                instrument.setDisambiguation(child.getText());
            } else if (DESCRIPTION.equals(child.getName())) {
                instrument.setDescription(child.getText());
            }
            //2014-09-20 still not usable
            //else if (ANNOTATION.equals(child.getName())) {
            //     artist.setAnnotation(createAnnotation(child));
            //}
            else if (ALIASLIST.equals(child.getName())) {
                addAliases(child, instrument);
            } else if (RELATIONLIST.equals(child.getName())) {
                addRelationsToEntity(child, instrument.getRelationList());
            } else {
                log.warning("Unrecognised Instrument element: " + child.getName());
            }
        }
        return instrument;
    }

    protected SeriesWs2 createSeries(Element node) {
        SeriesWs2 series = new SeriesWs2();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {

            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(ID)) {
                series.setIdUri(MbUtils.convertIdToURI(attribute.getValue(), PLACE));
            } else if (attribute.getName().equals(TYPE)) {
                series.setTypeUri(attribute.getValue());
            } else if (attribute.getName().equals(SCORE)) {
                //Ignore.
            } else {
                log.warning("Unrecognised Series attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (NAME.equals(child.getName())) {
                series.setName(child.getText());
            } else if (DISAMBIGUATION.equals(child.getName())) {
                series.setDisambiguation(child.getText());
            } else if (ORDERINGATTRIBUTE.equals(child.getName())) {
                series.setOrderingAttribute(child.getText());
            }
            //2014-09-20 still not usable
            //else if (ANNOTATION.equals(child.getName())) {
            //     artist.setAnnotation(createAnnotation(child));
            //}
            else if (ALIASLIST.equals(child.getName())) {
                addAliases(child, series);
            } else if (RELATIONLIST.equals(child.getName())) {
                addRelationsToEntity(child, series.getRelationList());
            } else {
                log.warning("Unrecognised Series element: " + child.getName());
            }
        }
        return series;
    }
    //------------------------------------------------------------------------//

    protected String createMessage(Element node) {
        return node.getValue();
    }

    protected CollectionWs2 createCollection(Element node) {
        CollectionWs2 collection = new CollectionWs2();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(ID)) {
                collection.setIdUri(MbUtils.convertIdToURI(attribute.getValue(), COLLECTION));
            } else {
                log.warning("Unrecognised Collection attribute: " + attribute.getName());
            }
        }
        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();

            if (EDITOR.equals(child.getName())) {
                collection.setEditor(child.getText());
            } else if (NAME.equals(child.getName())) {
                collection.setName(child.getText());
            } else if (RELEASELIST.equals(child.getName())) {
                addReleasesToList(child, collection.getReleaseList());
            } else {
                log.warning("Unrecognised Collection element: " + child.getName());
            }
        }
        return collection;
    }

    protected AnnotationWs2 createAnnotation(Element node) {
        AnnotationWs2 annotation = new AnnotationWs2();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(TYPE)) {
                annotation.setType(MbUtils.convertTypeToURI(attribute.getValue(), NS_MMD_2_PREFIX));
            } else if (attribute.getName().equals(SCORE)) {
                //ignore.
            } else {
                log.warning("Unrecognised Annotation attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();

            if (ENTITY.equals(child.getName())) {
                annotation.setEntity(child.getText());
            } else if (NAME.equals(child.getName())) {
                annotation.setName(child.getText());
            } else if (TEXT.equals(child.getName())) {
                annotation.setText(child.getText());
            } else {
                log.warning("Unrecognised Annotation element: " + child.getName());
            }
        }
        return annotation;
    }

    protected DiscWs2 createDisc(Element node) {
        return createDisc(node, null);

    }

    protected DiscWs2 createDisc(Element node, MediumWs2 medium) {
        DiscWs2 disc = new DiscWs2();
        disc.setMedium(medium);
        // d.setDiscId(node.getAttributeValue(ID));

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {

            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(ID)) {
                disc.setDiscId(attribute.getValue());
            } else {
                log.warning("Unrecognised Disk attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (SECTORS.equals(child.getName())) {
                disc.setSectors(getInt(child.getText()));
            } else if (RELEASELIST.equals(child.getName())) {
                addReleasesToList(child, disc.getReleaseList());
            } else {
                log.warning("Unrecognised Disc element: " + child.getName());
            }
        }
        return disc;
    }

    protected PuidWs2 createPuid(Element node) {
        PuidWs2 puid = new PuidWs2();
        //puid.setId(node.getAttributeValue(ID));

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {

            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(ID)) {
                puid.setId(attribute.getValue());
            } else {
                log.warning("Unrecognised Puid attribute: " + attribute.getName());
            }
        }
        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (RECORDINGLIST.equals(child.getName())) {
                addRecordingToList(child, puid.getRecordingList());
            } else {
                log.warning("Unrecognised Puid element: " + child.getName());
            }
        }
        return puid;
    }

    //-------------------------------------------------------------------------//
    protected RelationWs2 createRelation(Element node, String targetType) {

        RelationWs2 relation = new RelationWs2();

        relation.setTargetType(targetType);
        String resType = MbUtils.extractTypeFromURI(targetType);
        EntityWs2 target = null;

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {

            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(TYPE)) {
                relation.setType(MbUtils.convertTypeToURI(attribute.getValue(), NS_REL_2_PREFIX));
            } else if (attribute.getName().equals(TYPEID)) {
                relation.setTypeId(MbUtils.extractUuid(attribute.getValue(), resType));
            } else {
                log.warning("Unrecognised Relation attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;

        while (itr.hasNext()) {
            child = (Element) itr.next();

            if (TARGET.equals(child.getName())) {

                try {
                    relation.setTargetId(MbUtils.extractUuid(child.getText(), resType));
                } catch (IllegalArgumentException ex) {
                    relation.setTargetId(child.getText());
                }
            } else if (ORDERINGKEY.equals(child.getName())) {
                if (!child.getText().isEmpty()) {
                    relation.setOrderingKey(getInt(child.getText()));
                } else {
                    relation.setOrderingKey(0);
                }
            } else if (DIRECTION.equals(child.getName())) {
                relation.setDirection(getText(child.getText(), directionPattern, RelationWs2.DIR_BOTH));
            } else if (RELATIONATTRIBUTELIST.equals(child.getName())) {
                relation.setAttributes(createRelationAttributesList(child));
            } else if (BEGIN.equals(child.getName())) {
                relation.setBeginDate(child.getText());
            } else if (END.equals(child.getName())) {
                relation.setEndDate(child.getText());
            } else if (ENDED.equals(child.getName())) {
                relation.setEnded(getBoolean(child.getText()));
            } else if (ARTIST.equals(child.getName())) {
                target = createArtist(child);
            } else if (RELEASE.equals(child.getName())) {
                target = createRelease(child);
            } else if (RELEASEGROUP.equals(child.getName())) {
                target = createReleaseGroup(child);
            } else if (RECORDING.equals(child.getName())) {
                target = createRecording(child);
            } else if (LABEL.equals(child.getName())) {
                target = createLabel(child);
            } else if (WORK.equals(child.getName())) {
                target = createWork(child);
            } else if (AREA.equals(child.getName())) {
                target = createArea(child);
            } else if (PLACE.equals(child.getName())) {
                target = createPlace(child);
            } else if (INSTRUMENT.equals(child.getName())) {
                target = createInstrument(child);
            } else if (SERIES.equals(child.getName())) {
                target = createSeries(child);
            }
            // URL is ok this way..
            else {
                log.warning("Unrecognised Relation element: " + child.getName());
            }
        }
        if (relation.getType() == null) {
            return null;
        }
        if (relation.getTargetType() == null) {
            return null;
        }

        if (relation.getTargetId() == null) {
            relation.setTargetId(target.getId());
        }

        relation.setTarget(target);
        return relation;
    }

    private List<String> createRelationAttributesList(Element node) {
        List<String> attributes
                = new ArrayList<String>();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();
            log.warning("Unrecognised Relation Attribute List attribute: " + attribute.getName());
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {

            child = (Element) itr.next();

            if (ATTRIBUTE.equals(child.getName())) {
                String ra = createRelationAttribute(child);
                attributes.add(ra);
            } else {
                log.warning("Unrecognised Relation Attribute List element: " + child.getName());
            }
        }
        return attributes;
    }

    private String createRelationAttribute(Element node) {

        String attrName = "";
        String attrValue = "";

        Iterator itrRelAttribute = node.getAttributes().iterator();
        while (itrRelAttribute.hasNext()) {

            Attribute attribute = (Attribute) itrRelAttribute.next();
            if (attribute.getName().equals(RELATIONATTRIBUTEVALUE)) {
                attrValue = attribute.getValue();
            } else if (attribute.getName().equals(RELATIONATTRIBUTECREDITEDAS)) {
                attrValue = RELATIONATTRIBUTECREDITEDAS + ": " + attribute.getValue();
            } else {
                log.warning("Unrecognised Relation Attribute attribute: " + attribute.getName());
                // retturned as generic.
                attrValue = attribute.getName() + ": " + attribute.getValue();
            }
        }
        itrRelAttribute = node.getChildren().iterator();
        Element el;
        while (itrRelAttribute.hasNext()) {
            el = (Element) itrRelAttribute.next();
            log.warning("Unrecognised Relation Attribute element: " + el.getName());
        }
        attrName = node.getText();
        if (attrValue.isEmpty()) {
            return attrName;
        }
        return attrName + " = " + attrValue;
    }
    //--------------------------------------------------------------------------//

    protected void addLabelResults(Element node,
                                   LabelSearchResultsWs2 labelResults) {
        if (labelResults == null) {
            throw new NullPointerException();
        }

        updateListElement(node, labelResults);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            LabelResultWs2 r = new LabelResultWs2();
            r.setLabel(createLabel(child));
            r.setScore(getIntAttr(child, SCORE, NS_EXT_2));
            if (r.getLabel() != null) {
                labelResults.addLabelResult(r);
            }
        }
    }

    protected void addArtistResults(Element node,
                                    ArtistSearchResultsWs2 artistResults) {
        if (artistResults == null) {
            throw new NullPointerException();
        }

        updateListElement(node, artistResults);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            ArtistResultWs2 r = new ArtistResultWs2();
            r.setArtist(createArtist(child));
            r.setScore(getIntAttr(child, SCORE, NS_EXT_2));
            if (r.getArtist() != null) {
                artistResults.addArtistResult(r);
            }
        }
    }

    protected void addReleaseGroupResults(Element node,
                                          ReleaseGroupSearchResultsWs2 releaseGroupResults) {
        if (releaseGroupResults == null) {
            throw new NullPointerException();
        }

        updateListElement(node, releaseGroupResults);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            ReleaseGroupResultWs2 r = new ReleaseGroupResultWs2();
            r.setReleaseGroup(createReleaseGroup(child));
            r.setScore(getIntAttr(child, SCORE, NS_EXT_2));
            if (r.getReleaseGroup() != null) {
                releaseGroupResults.addReleaseGroupResult(r);
            }
        }
    }

    protected void addReleaseResults(Element node,
                                     ReleaseSearchResultsWs2 releaseResults) {
        if (releaseResults == null) {
            throw new NullPointerException();
        }

        updateListElement(node, releaseResults);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            ReleaseResultWs2 r = new ReleaseResultWs2();
            r.setRelease(createRelease(child));
            r.setScore(getIntAttr(child, SCORE, NS_EXT_2));
            if (r.getRelease() != null) {
                releaseResults.addReleaseResult(r);
            }
        }
    }

    protected void addWorkResults(Element node,
                                  WorkSearchResultsWs2 workResults) {
        if (workResults == null) {
            throw new NullPointerException();
        }

        updateListElement(node, workResults);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            WorkResultWs2 r = new WorkResultWs2();
            r.setWork(createWork(child));
            r.setScore(getIntAttr(child, SCORE, NS_EXT_2));
            if (r.getWork() != null) {
                workResults.addWorkResult(r);
            }
        }
    }

    protected void addRecordingResults(Element node,
                                       RecordingSearchResultsWs2 recordingResults) {
        if (recordingResults == null) {
            throw new NullPointerException();
        }

        updateListElement(node, recordingResults);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            RecordingResultWs2 r = new RecordingResultWs2();
            r.setRecording(createRecording(child));
            r.setScore(getIntAttr(child, SCORE, NS_EXT_2));
            if (r.getRecording() != null) {
                recordingResults.addRecordingResult(r);
            }
        }
    }

    // 2014-09-20 Added support for new entities.
    protected void addAreaResults(Element node,
                                  AreaSearchResultsWs2 areaResults) {
        if (areaResults == null) {
            throw new NullPointerException();
        }

        updateListElement(node, areaResults);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            AreaResultWs2 r = new AreaResultWs2();
            r.setArea(createArea(child));
            r.setScore(getIntAttr(child, SCORE, NS_EXT_2));
            if (r.getArea() != null) {
                areaResults.addAreaResult(r);
            }
        }
    }

    protected void addPlaceResults(Element node,
                                   PlaceSearchResultsWs2 placeResults) {
        if (placeResults == null) {
            throw new NullPointerException();
        }

        updateListElement(node, placeResults);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            PlaceResultWs2 r = new PlaceResultWs2();
            r.setPlace(createPlace(child));
            r.setScore(getIntAttr(child, SCORE, NS_EXT_2));
            if (r.getPlace() != null) {
                placeResults.addPlaceResult(r);
            }
        }
    }

    protected void addInstrumentResults(Element node,
                                        InstrumentSearchResultsWs2 instrumentResults) {
        if (instrumentResults == null) {
            throw new NullPointerException();
        }

        updateListElement(node, instrumentResults);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            InstrumentResultWs2 r = new InstrumentResultWs2();
            r.setInstrument(createInstrument(child));
            r.setScore(getIntAttr(child, SCORE, NS_EXT_2));
            if (r.getInstrument() != null) {
                instrumentResults.addInstrumentResult(r);
            }
        }
    }

    protected void addSeriesResults(Element node,
                                    SeriesSearchResultsWs2 seriesResults) {
        if (seriesResults == null) {
            throw new NullPointerException();
        }

        updateListElement(node, seriesResults);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            SeriesResultWs2 r = new SeriesResultWs2();
            r.setSeries(createSeries(child));
            r.setScore(getIntAttr(child, SCORE, NS_EXT_2));
            if (r.getSeries() != null) {
                seriesResults.addSeriesResult(r);
            }
        }
    }

    //--------------------------------------------------------------------------//
    protected void addCollectionResults(Element node,
                                        CollectionSearchResultsWs2 collectionResults) {
        if (collectionResults == null) {
            throw new NullPointerException();
        }

        updateListElement(node, collectionResults);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            CollectionResultWs2 r = new CollectionResultWs2();
            r.setCollection(createCollection(child));
            r.setScore(getIntAttr(child, SCORE, NS_EXT_2));
            if (r.getCollection() != null) {
                collectionResults.addCollectionResult(r);
            }
        }
    }

    protected void addAnnotationResults(Element node,
                                        AnnotationSearchResultsWs2 annotationResults) {
        if (annotationResults == null) {
            throw new NullPointerException();
        }

        updateListElement(node, annotationResults);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            AnnotationResultWs2 r = new AnnotationResultWs2();
            r.setAnnotation(createAnnotation(child));
            r.setScore(getIntAttr(child, SCORE, NS_EXT_2));
            if (r.getAnnotation() != null) {
                annotationResults.addAnnotationResult(r);
            }
        }
    }

    //-------------------------------------------------------------------------//
    protected void addLabelsToList(Element node,
                                   LabelListWs2 labelList) {
        if (labelList == null) {
            throw new NullPointerException();
        }

        updateListElement(node, labelList);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (LABEL.equals(child.getName())) {
                labelList.addLabel(createLabel(child));
            }
        }
    }

    protected void addArtistToList(Element node,
                                   ArtistListWs2 artistList) {
        if (artistList == null) {
            throw new NullPointerException();
        }

        updateListElement(node, artistList);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (ARTIST.equals(child.getName())) {
                artistList.addArtist(createArtist(child));
            }
        }
    }

    protected void addReleaseGroupsToList(Element node,
                                          ReleaseGroupListWs2 releaseGroupList) {
        if (releaseGroupList == null) {
            throw new NullPointerException();
        }

        updateListElement(node, releaseGroupList);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (RELEASEGROUP.equals(child.getName())) {
                releaseGroupList.addReleaseGroup(createReleaseGroup(child));
            }
        }
    }

    protected void addReleasesToList(Element node,
                                     ReleaseListWs2 releaseList) {
        if (releaseList == null) {
            throw new NullPointerException();
        }

        updateListElement(node, releaseList);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (RELEASE.equals(child.getName())) {
                releaseList.addRelease(createRelease(child));
            }
        }
    }

    protected void addWorkToList(Element node,
                                 WorkListWs2 workList) {
        if (workList == null) {
            throw new NullPointerException();
        }

        updateListElement(node, workList);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (WORK.equals(child.getName())) {
                workList.addWork(createWork(child));
            }
        }
    }

    protected void addRecordingToList(Element node,
                                      RecordingListWs2 recordingList) {
        if (recordingList == null) {
            throw new NullPointerException();
        }

        updateListElement(node, recordingList);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (RECORDING.equals(child.getName())) {
                recordingList.addRecording(createRecording(child));
            }
        }
    }

    protected void addAreaToList(Element node,
                                 AreaListWs2 areaList) {
        if (areaList == null) {
            throw new NullPointerException();
        }

        updateListElement(node, areaList);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (AREA.equals(child.getName())) {
                areaList.addArea(createArea(child));
            }
        }
    }

    protected void addPlaceToList(Element node,
                                  PlaceListWs2 placeList) {
        if (placeList == null) {
            throw new NullPointerException();
        }

        updateListElement(node, placeList);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (PLACE.equals(child.getName())) {
                placeList.addPlace(createPlace(child));
            }
        }
    }

    protected void addInstrumentToList(Element node,
                                       InstrumentListWs2 instrumentList) {
        if (instrumentList == null) {
            throw new NullPointerException();
        }

        updateListElement(node, instrumentList);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (INSTRUMENT.equals(child.getName())) {
                instrumentList.addInstrument(createInstrument(child));
            }
        }
    }

    protected void addSeriesToList(Element node,
                                   SeriesListWs2 seriesList) {
        if (seriesList == null) {
            throw new NullPointerException();
        }

        updateListElement(node, seriesList);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (SERIES.equals(child.getName())) {
                seriesList.addSeries(createSeries(child));
            }
        }
    }

    //-------------------------------------------------------------------------//
    protected List<AliasWs2> createAliasList(Element node) {

        List<AliasWs2> aliases = new ArrayList<AliasWs2>();
        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {

            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(COUNT)) {
                //ignore
            } else {
                log.warning("Unrecognised AliasList attribute: " + attribute.getName());
            }
        }
        itr = node.getChildren().iterator();
        Element child;

        while (itr.hasNext()) {
            child = (Element) itr.next();
            AliasWs2 alias = new AliasWs2();

            Iterator itrC = child.getAttributes().iterator();
            while (itrC.hasNext()) {

                Attribute attribute = (Attribute) itrC.next();

                if (attribute.getName().equals(LOCALE)) {
                    alias.setLocale(getAttr(child, LOCALE, localePattern, "en", null));
                } else if (attribute.getName().equals(SORTNAME)) {
                    alias.setSortName(attribute.getValue());
                } else if (attribute.getName().equals(PRIMARY)) {
                    alias.setPrimary(attribute.getValue());
                } else if (attribute.getName().equals(TYPE)) {
                    alias.setType(attribute.getValue());
                } else if (attribute.getName().equals(BEGINDATE)) {
                    alias.setBeginDate(child.getText());
                } else if (attribute.getName().equals(ENDDATE)) {
                    alias.setEndDate(child.getText());
                }
                // 2014-09-14 not in mmd.
                //else if (attribute.getName().equals(ENDED)){
                //      alias.setEnded(getBoolean(child.getText()));
                //}
                else {
                    log.warning("Unrecognised Alias attribute: " + attribute.getName());
                }
            }
            if (ALIAS.equals(child.getName())) {
                alias.setValue(child.getText());
                aliases.add(alias);
            } else {
                log.warning("Unrecognised Alias element: " + child.getName());
            }
        }
        return aliases;
    }

    protected void addAliases(Element node, EntityWs2 entity) {

        List<AliasWs2> aliases = createAliasList(node);

        //entity.setAliases(aliases); retain previous existings.
        Iterator itrC = aliases.iterator();

        while (itrC.hasNext()) {
            AliasWs2 alias = (AliasWs2) itrC.next();
            entity.addAlias(alias);
        }
    }

    protected void addTags(Element node, EntityWs2 entity) {
        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();
            log.warning("Unrecognised TagList attribute: " + attribute.getName());
        }
        itr = node.getChildren().iterator();
        Element child;

        while (itr.hasNext()) {
            child = (Element) itr.next();
            TagWs2 tag = new TagWs2();

            Iterator itrC = child.getAttributes().iterator();
            while (itrC.hasNext()) {

                Attribute attribute = (Attribute) itrC.next();

                if (attribute.getName().equals(COUNT)) {
                    try {
                        tag.setCount(attribute.getLongValue());
                    } catch (DataConversionException ex) {
                        Logger.getLogger(JDOMParserWs2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    log.warning("Unrecognised Tag attribute: " + attribute.getName());
                }
            }
            itrC = child.getChildren().iterator();
            Element el;
            while (itrC.hasNext()) {
                el = (Element) itrC.next();
                Iterator itrE = el.getAttributes().iterator();
                while (itrE.hasNext()) {
                    Attribute attribute = (Attribute) itrE.next();
                    log.warning("Unrecognised TagName attribute: " + attribute.getName());
                }

                if (NAME.equals(el.getName())) {
                    tag.setName(el.getText());
                    entity.addTag(tag);
                } else {
                    log.warning("Unrecognised Tag element: " + el.getName());
                }
            }
        }
    }

    protected void addUserTags(Element node, EntityWs2 entity) {
        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();
            log.warning("Unrecognised UserTagList attribute: " + attribute.getName());
        }
        itr = node.getChildren().iterator();
        Element child;

        while (itr.hasNext()) {
            child = (Element) itr.next();
            TagWs2 tag = new TagWs2();

            Iterator itrC = child.getAttributes().iterator();
            while (itrC.hasNext()) {

                Attribute attribute = (Attribute) itrC.next();
                log.warning("Unrecognised userTag attribute: " + attribute.getName());
            }
            itrC = child.getChildren().iterator();
            Element el;
            while (itrC.hasNext()) {
                el = (Element) itrC.next();
                Iterator itrE = el.getAttributes().iterator();
                while (itrE.hasNext()) {
                    Attribute attribute = (Attribute) itrE.next();
                    log.warning("Unrecognised TagName attribute: " + attribute.getName());
                }

                if (NAME.equals(el.getName())) {
                    tag.setName(el.getText());
                    entity.addUserTag(tag);
                } else {
                    log.warning("Unrecognised userTag element: " + el.getName());
                }
            }
        }
    }

    protected ArtistCreditWs2 createArtistCredit(Element node) {

        List<NameCreditWs2> nameCredits = new ArrayList<NameCreditWs2>();

        Iterator itrNameCredit = node.getAttributes().iterator();
        while (itrNameCredit.hasNext()) {
            Attribute attribute = (Attribute) itrNameCredit.next();
            log.warning("Unrecognised ArtistCredit attribute: " + attribute.getName());
        }

        itrNameCredit = node.getChildren().iterator();
        Element elNameCredit;
        while (itrNameCredit.hasNext()) {
            elNameCredit = (Element) itrNameCredit.next();

            if (NAMECREDIT.equals(elNameCredit.getName())) {

                NameCreditWs2 nameCredit = new NameCreditWs2();

                Iterator itrNameCreditAttributes = elNameCredit.getAttributes().iterator();
                while (itrNameCreditAttributes.hasNext()) {
                    Attribute attribute = (Attribute) itrNameCreditAttributes.next();

                    if (attribute.getName().equals(JOINPHRASE)) {
                        nameCredit.setJoinphrase(attribute.getValue());
                    } else {
                        log.warning("Unrecognised Name Credit attribute: " + attribute.getName());
                    }
                }

                Iterator itrArtistOrName = elNameCredit.getChildren().iterator();

                Element elArtistOrName;
                while (itrArtistOrName.hasNext()) {
                    elArtistOrName = (Element) itrArtistOrName.next();

                    if (NAME.equals(elArtistOrName.getName())) {
                        nameCredit.setName(elArtistOrName.getText());

                        Iterator itrNameAttributes = elArtistOrName.getAttributes().iterator();
                        while (itrNameAttributes.hasNext()) {
                            Attribute attribute = (Attribute) itrNameAttributes.next();
                            log.warning("Unrecognised NameCredit, name attribute: " + attribute.getName());
                        }
                    } else if (ARTIST.equals(elArtistOrName.getName())) {
                        nameCredit.setArtist(createArtist(elArtistOrName));
                    } else {
                        log.warning("Unrecognised Name Credit element: " + elArtistOrName.getName());
                    }
                }
                nameCredits.add(nameCredit);
            } else {
                log.warning("Unrecognised Artist Credit element: " + elNameCredit.getName());
            }
        }
        return new ArtistCreditWs2(nameCredits);
    }

    protected LifeSpanWs2 createLifeSpan(Element node) {
        String begin = null;
        String end = null;
        boolean ended = false;

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();
            log.warning("Unrecognised LifeSpan attribute: " + attribute.getName());
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (BEGIN.equals(child.getName())) {
                begin = (child.getText());

            } else if (END.equals(child.getName())) {
                end = (child.getText());
            } else if (ENDED.equals(child.getName())) {
                ended = getBoolean(child.getText());
            } else {
                log.warning("Unrecognised Life Span element: " + child.getName());
            }
        }
        return new LifeSpanWs2(begin, end, ended);
    }

    protected LabelInfoListWs2 createLabelInfoList(Element node) {
        List<LabelInfoWs2> LabelInfos
                = new ArrayList<LabelInfoWs2>();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(COUNT) ||
                    attribute.getName().equals(OFFSET)) {
                //ignore (see updateListElement).
            } else {
                log.warning("Unrecognised LabelInfoList attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;

        while (itr.hasNext()) {
            child = (Element) itr.next();

            Iterator itrC = child.getAttributes().iterator();

            while (itrC.hasNext()) {
                Attribute attribute = (Attribute) itrC.next();
                log.warning("Unrecognised LabelInfo attribute: " + attribute.getName());
            }

            if (LABELINFO.equals(child.getName())) {

                LabelInfoWs2 labelInfo = new LabelInfoWs2();

                Iterator itr2 = child.getChildren().iterator();

                Element el;
                while (itr2.hasNext()) {
                    el = (Element) itr2.next();

                    if (CATALOGNUMBER.equals(el.getName())) {
                        labelInfo.setCatalogNumber(el.getText());

                        Iterator itrC2 = el.getAttributes().iterator();
                        while (itrC2.hasNext()) {
                            Attribute attribute = (Attribute) itrC2.next();
                            log.warning("Unrecognised labelinfo catno attribute: " + attribute.getName());
                        }
                    } else if (LABEL.equals(el.getName())) {
                        labelInfo.setLabel(createLabel(el));
                    } else {
                        log.warning("Unrecognised Label Info element: " + child.getName());
                    }
                }
                LabelInfos.add(labelInfo);
            } else {
                log.warning("Unrecognised Life Info List element: " + child.getName());
            }
        }
        LabelInfoListWs2 out = new LabelInfoListWs2(LabelInfos);
        updateListElement(node, out);

        return out;
    }

    protected CoverArtArchiveWs2 createCoverArtArchive(Element node, String mbid) {

        boolean artwork = false;
        boolean front = false;
        boolean back = false;
        int count = 0;

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();
            log.warning("Unrecognised Cover Art Archive attribute: " + attribute.getName());
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (ARTWORK.equals(child.getName())) {
                artwork = getBoolean(child.getText());

            } else if (COUNT.equals(child.getName())) {
                count = getInt(child.getText());

            } else if (FRONT.equals(child.getName())) {
                front = getBoolean(child.getText());
            } else if (BACK.equals(child.getName())) {
                back = getBoolean(child.getText());
            } else {
                log.warning("Unrecognised Cover Art Archive element: " + child.getName());
            }
        }
        return new CoverArtArchiveWs2(artwork, count, front, back, mbid);
    }

    protected MediumListWs2 createMediumList(Element node) {
        List<MediumWs2> media
                = new ArrayList<MediumWs2>();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(COUNT) ||
                    attribute.getName().equals(OFFSET)) {
                //ignore (see updateListElement).
            } else {
                log.warning("Unrecognised MediumList attribute: " + attribute.getName());
            }
        }

        int trackCount = 0;
        long duration = 0L;

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {

            child = (Element) itr.next();

            if (TRACKCOUNT.equals(child.getName())) {
                trackCount = getInt(child.getText());
            } else if (MEDIUM.equals(child.getName())) {
                MediumWs2 m = createMedium(child);
                media.add(m);
            } else {
                log.warning("Unrecognised Medium List element: " + child.getName());
            }
        }
        MediumListWs2 out = new MediumListWs2(media);
        if (trackCount == 0) {
            for (MediumWs2 m : media) {
                trackCount = trackCount + m.getTracksCount();
            }
        }
        out.setTracksCount(trackCount);
        updateListElement(node, out);

        return out;
    }

    protected ReleaseEventListWs2 createReleaseEventList(Element node) {
        List<ReleaseEventWs2> events
                = new ArrayList<ReleaseEventWs2>();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(COUNT) ||
                    attribute.getName().equals(OFFSET)) {
                //ignore (see updateListElement).
            } else {
                log.warning("Unrecognised ReleaseEventList attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {

            child = (Element) itr.next();

            if (RELEASEEVENT.equals(child.getName())) {
                ReleaseEventWs2 e = createReleaseEvent(child);
                events.add(e);
            } else {
                log.warning("Unrecognised Release Event List element: " + child.getName());
            }
        }
        ReleaseEventListWs2 out = new ReleaseEventListWs2(events);
        updateListElement(node, out);

        return out;
    }

    protected TrackListWs2 createTrackList(Element node, MediumWs2 medium) {

        List<TrackWs2> tracks = new ArrayList<TrackWs2>();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();
            if (attribute.getName().equals(COUNT) ||
                    attribute.getName().equals(OFFSET)) {
                //ignore (see updateListElement).
            } else {
                log.warning("Unrecognised TrackList attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (TRACK.equals(child.getName())) {
                tracks.add(createTrack(child, medium));
            } else {
                log.warning("Unrecognised TrackList element: " + child.getName());
            }
        }
        TrackListWs2 out = new TrackListWs2(tracks);
        updateListElement(node, out);

        return out;
    }

    protected DiscListWs2 createDiscList(Element node, MediumWs2 medium) {
        List<DiscWs2> discs
                = new ArrayList<DiscWs2>();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();
            if (attribute.getName().equals(COUNT) ||
                    attribute.getName().equals(OFFSET)) {
                //ignore (see updateListElement).
            } else {
                log.warning("Unrecognised DiscList attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (DISC.equals(child.getName())) {
                discs.add(createDisc(child, medium));
            } else {
                log.warning("Unrecognised Disc List element: " + child.getName());
            }
        }
        DiscListWs2 out = new DiscListWs2(discs);
        updateListElement(node, out);

        return out;
    }

    protected ReleaseEventWs2 createReleaseEvent(Element node) {
        ReleaseEventWs2 event = new ReleaseEventWs2();
        //isrc.setId(node.getAttributeValue(ID));

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {

            Attribute attribute = (Attribute) itr.next();
            log.warning("Unrecognised Release Event attribute: " + attribute.getName());
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (DATE.equals(child.getName())) {
                event.setDateString(child.getValue());
            } else if (AREA.equals(child.getName())) {
                event.setArea(createArea(child));
            } else {
                log.warning("Unrecognised Release Event element: " + child.getName());
            }
        }
        return event;
    }

    protected MediumWs2 createMedium(Element node) {
        MediumWs2 medium = new MediumWs2();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();
            log.warning("Unrecognised Medium attribute: " + attribute.getName());
        }

        itr = node.getChildren().iterator();

        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();

            if (TITLE.equals(child.getName())) {
                medium.setTitle(child.getText());
            } else if (POSITION.equals(child.getName())) {
                medium.setPosition(getInt(child.getText()));
            } else if (FORMAT.equals(child.getName())) {
                medium.setFormat(child.getText());
            } else if (TRACKLIST.equals(child.getName())) {
                medium.setTracksCount(getInt(child.getAttributeValue(COUNT)));
                medium.setTrackList(createTrackList(child, medium));

            } else if (DISCLIST.equals(child.getName())) {
                medium.setDiscCount(getInt(child.getAttributeValue(COUNT)));
                medium.setDiscList(createDiscList(child, medium));
            } else {
                log.warning("Unrecognised Medium element: " + child.getName());
            }
        }
        return medium;
    }

    protected TrackWs2 createTrack(Element node, MediumWs2 medium) {
        TrackWs2 t = new TrackWs2();
        t.setMedium(medium);

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {
            Attribute attribute = (Attribute) itr.next();
            if (attribute.getName().equals(ID)) {
                t.setId(attribute.getValue());
            } else {
                log.warning("Unrecognised Track attribute: " + attribute.getName());
            }
        }

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            if (POSITION.equals(child.getName())) {
                t.setPosition(getInt(child.getText()));
            } else if (NUMBER.equals(child.getName())) {
                t.setNumber(getInt(child.getText()));
            } else if (TITLE.equals(child.getName())) {
                t.setTitle(child.getText());
            } else if (ARTISTCREDIT.equals(child.getName())) {
                t.setArtistCredit(createArtistCredit(child));
            } else if (RECORDING.equals(child.getName())) {
                t.setRecording(createRecording(child));
            } else if (LENGTH.equals(child.getName())) {
                String d = child.getValue();
                if (d != null) {
                    try {
                        Long l = Long.parseLong(d);
                        t.setDurationInMillis(l);
                    } catch (NumberFormatException e) {
                        log.warning("Illegal duration value!");
                    }
                }
            } else {
                log.warning("Unrecognised Track element: " + child.getName());
            }
        }
        return t;
    }

    protected RatingsWs2 createRating(Element node) {
        RatingsWs2 rating = new RatingsWs2();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {

            Attribute attribute = (Attribute) itr.next();

            if (attribute.getName().equals(VOTESCOUNT)) {
                try {
                    rating.setVotesCount(attribute.getLongValue());
                } catch (DataConversionException ex) {
                    Logger.getLogger(JDOMParserWs2.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                log.warning("Unrecognised Rating attribute: " + attribute.getName());
            }
        }
        rating.setAverageRating(Float.parseFloat(node.getValue()));

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            log.warning("Unrecognised Rating element: " + child.getName());
        }

        return rating;
    }

    protected RatingsWs2 createUserRating(Element node) {
        RatingsWs2 rating = new RatingsWs2();

        Iterator itr = node.getAttributes().iterator();
        while (itr.hasNext()) {

            Attribute attribute = (Attribute) itr.next();
            log.warning("Unrecognised Rating attribute: " + attribute.getName());

        }
        rating.setAverageRating(Float.parseFloat(node.getValue()));

        itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            log.warning("Unrecognised Rating element: " + child.getName());
        }

        return rating;
    }

    //-------------------------------------------------------------------------//
    protected void addRelationsToEntity(Element node,
                                        RelationListWs2 relationList) {

        String targetType = MbUtils.convertTypeToURI(
                node.getAttributeValue(TARGETTYPE), EntityWs2.NS_REL_2_PREFIX);

        if (targetType == null) {
            return;
        }

        if (relationList == null) {
            throw new NullPointerException();
        }

        updateListElement(node, relationList);

        Iterator itr = node.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            RelationWs2 rel;
            if (RELATION.equals(child.getName())) {
                rel = createRelation(child, targetType);
                if (rel != null) {
                    relationList.addRelation(rel);
                }
            }
        }
    }

    //-------------------------------------------------------------------//
    protected void updateListElement(Element node, ListElement le) {
        le.setCount(getLongAttr(node, COUNT));
        le.setOffset(getLongAttr(node, OFFSET));
    }

    protected String getAttr(Element element, String attrName) {
        return getAttr(element, attrName, null, null, null);
    }

    protected String getAttr(Element element, String attrName, Pattern regex) {
        return getAttr(element, attrName, regex, null, null);
    }

    /**
     * Returns an attribute of the given element.
     * <p>
     * If there is no attribute with that name or the attribute doesn'recording
     * match the regular expression, default is returned.
     *
     * @param element
     * @param attrName
     * @param regex
     * @param def
     * @param ns
     * @return A String
     */
    protected String getAttr(Element element,
                             String attrName,
                             Pattern regex,
                             String def,
                             Namespace ns) {
        Attribute at;
        if (ns != null) {
            at = element.getAttribute(attrName, ns);
        } else {
            at = element.getAttribute(attrName);
        }

        if (at != null) {
            String txt = at.getValue();
            return getText(txt, regex, def);
        }
        return def;
    }

    protected String getText(String txt, Pattern regex, String def) {
        if (txt != null && regex != null) {
            Matcher m = regex.matcher(txt);
            if (m.matches()) {
                return txt;
            } else {
                return def;
            }
        }
        return txt;
    }

    protected Long getLongAttr(Element element, String attrName) {
        String value = getAttr(element, attrName);
        if (value != null) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    protected Integer getIntAttr(Element element, String attrName) {
        return getIntAttr(element, attrName, null);
    }

    protected Integer getIntAttr(Element element,
                                 String attrName,
                                 Namespace ns) {
        String value = getAttr(element, attrName, null, null, ns);
        return getInt(value);
    }

    protected List<String> getArrayElementsAsList(Element element) {
        List<String> out = new ArrayList<String>();

        Iterator itr = element.getChildren().iterator();
        Element child;
        while (itr.hasNext()) {
            child = (Element) itr.next();
            String s = child.getText();
            out.add(s);
        }
        return out;
    }

    private int getInt(String str) {

        if (str != null) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

    private boolean getBoolean(String str) {

        return str != null && !str.isEmpty() && str.equals("true");
    }
}
