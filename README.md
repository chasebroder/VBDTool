# VBD
This Value-Based Drafting (VBD) Tool is a fantasy football draft-day assistant that calculates value scores for each player. This score is based on a number of factors, including how many players of the same position you currently have on your team and how that player compares to other available players of the same position. This tool scrapes fantasy football web data from FantasyPros to calculate projections. The draft lobby allows users to add roster and draft settings more specific to their league.

## Possible improvements
The code was recently ported to a maven project to improve dependency management and maintainability. The tool was developed in 2018, so there are plenty of possible improvements to get the tool ready for the 2022 season:
* Remove commented code
* Make code more portable for readability purposes
* Clean up Maven installation
* Add ability to filter players by position on draft screen

## How to run
After cloning the code to a local repository, the tool can be used by running "mvn javafx:run" in the terminal. Make sure at least Java 11 is installed on the local machine, along with Maven 3.8.