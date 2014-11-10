# darcy-salesforce
[![Build Status](https://travis-ci.org/darcy-framework/darcy-salesforce.svg?branch=master)](https://travis-ci.org/darcy-framework/darcy-salesforce)

Library of Salesforce element implementations.

# Features

- Salesforce UI elements often employ extra HTML and Javascript beyond standard HTML implementations. For instance, checkboxes may actually be image tags, and text inputs may actually be divs. This library has implementations of those elements that you can use when writing Salesforce page objects.
- Abstract base class for related lists that take advantage of darcy-ui's `Table` interface for flexible table interactions.
