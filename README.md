# testredirects
Sinatra server to test chromium issue 544225

To run, modify the source.rb file and set the destination variable to be your DNS entry, and execute the following:

gem install sinatra

ruby destination.rb -o 0.0.0.0 -p 4568 &

ruby source.rb -o 0.0.0.0

Then modify the com.jerehmiah.testredirects.MainActivity.java file so that the loadUrl points to your IP, then install/execute the accompanying Android app on a marshmallow device
