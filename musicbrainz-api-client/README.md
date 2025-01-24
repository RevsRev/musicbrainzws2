# MusicBrainz API Client

A new Java library for integrating with the MusicBrainz API.

# Why?

There are a few things about the [existing binding](../musicbrainzws2-java) that serve as motivation:
- It was out of date and required significant changes to update it to a more modern standard.
- There are no tests which makes future development difficult.
- It is under the GNU GPL 3 license, which makes propriety usage difficult. This author prefers more permissive open source licenses which are not copyleft.

In particular, the lack of testing means that a significant amount of time will need to be spent unpicking and
understanding the entire code base. It is likely that significant changes will be needed to be made in order to make
the source code amenable to unit and integration tests. Given this, it is this author's opinion that building a new
library from scratch is comparable in time and effort.