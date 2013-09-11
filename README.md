# Tweelings

**Tweelings** is a web project developed by Sergio Álvarez Suárez as part of his final assignment for the subject *Semantic Web* from the Master in Web Engineering @ University of Oviedo.

## Motivation

The three main goals by Tweelings are:

1. Be able to obtain thousands of tweets about an sportsman during his/her activity.
2. Be able to analyze the feeling of those tweets.
3. Print the results in a web page by using Google Chart Tools.

## Architecture

Tweelings was developed under the following architecture.

### Feeling analyzer

RESTful web service developed in Java (by using *Apache CXF*) which returns the feeling of any text by using the list of *affective ratings proposed by Warriner, Kuperman & Brysbaert* (in a **simplified** way).

For stemming words, Apache Lucene was used (Snowball implementation, specifically).

### Twitter Streaming

This Java *script* collects tweets from Twitter Streaming API according to the selected tokens.

Tweets collected can only be from users whose Twitter profile favorite language is Spanish. This decision was taken *ad hoc* to ensure that the majority of tweets were written in Spanish.

### Twitter Feeling analyzer

According to the tweets previously collected by the Twitter Streaming script, Twitter Feeling analyzer uses the Feeling Analyzer WS to obtain tweets' rating and grouping them by their publication time on a properties file.

This script was also developed in Java.

### Tweelings

Finally, Tweelings is the web application responsible for showing the results obtained by the previous applications.

Tweelings was developed by using Java, Struts2, OGNL and JavaScript/jQuery.
