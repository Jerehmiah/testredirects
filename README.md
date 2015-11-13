# testredirects
Sinatra server to test chromium issue 544225

To run execute the following:

gem install sinatra

ruby destination.rb -o 0.0.0.0 -p 4568 &

ruby source.rb -o 0.0.0.0

Then install/execute the accompanying Android app on a marshmallow device
