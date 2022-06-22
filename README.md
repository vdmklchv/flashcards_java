# FLASHCARDS

## About
Flashcards is a fun study app from JetBrains Academy.
It allows to add, import, export cards, learn them and track simple stats on cards.

## Build
The project can be built with Intellij Idea or compiled with usual javac.


## Pre-requisites
Java version 11 and upper installed

## How to use
Can be run with or without command-line arguments

### Allowed start arguments:
- "-import filename.txt" - imports cards from pre-formatted file right at the start of application
- "-export filename.txt" - saves cards to filename.txt on exit

### App commands:
- **add** - asks for new flashcard and adds it to program
- **remove** - asks for card to remove and removes it from program
- **import** - asks for filename to import from and imports from it if file exists
- **export** - asks for filename to export to and exports to it if file exists
- **ask** - starts quiz, asks for number of questions and asks for cards in order
- **exit** - exits the app (with exporting if specified during app run)
- **log** - asks for name of log file, logs all console contents to it and saves it to root of project
- **hardest card** - outputs the card with most mistakes
- **reset stats** - resets stats for errors for existing cards


