# GitHub Logs retriever
## Description
This tool allows one to retrieve the logs of a GitHub repository and displays them on a webpage.
This version mostly is an implementation of the backend, with the server and APIs working as expected. A more enhanced and pleasant look of the website itself is to be added in the future.

Currently, it only displays the PushEvents of a repository, since it is about them that the most visual information is, however, other events are also retrieved and serialized, and the tool can be further extended to display them as well.

Powered by [GitHub API](https://developer.github.com/v3/) and [Ktor](https://ktor.io/).