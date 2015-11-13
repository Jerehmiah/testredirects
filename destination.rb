require 'sinatra'

html = "<html><head><link type='text/css' rel='stylesheet' href=/css/style.css></head><body></body></html>"

get '/oid' do
	redirect to('/directory'), 303
end

get '/directory' do
	redirect to('/idp'), 303
end

get '/idp' do
	html
end