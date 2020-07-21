# TransactionApp
Android app for tracking expenses that can view the contents of a MySQL database and submit new transactions (works in conjunction with MySQL RESTful API)

The application is used in conjunction with a Tomcat server I host on my local network. I can record multiple transactions from anywhere using the app and then
submit them to my database while at home. An HTTP request is used to submit the data in a JSON format. If the HTTP response indicates a success, the submitted
transactions are cleared from the app's memory. It also uses an HTTP request to retrieve the current contents of the database so they can be shown to the user.
The url and port used for these requests are easily configurable from the Settings screen. A specific transaction can be selected to display additional data
that doesn't fit in the table format.
