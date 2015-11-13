require 'sinatra'
destination = 'http://mac-jj010933.northamerica.cerner.net:4568'

get '/302me' do
	redirect to(destination + '/oid')
end

get '/303me' do
	redirect to(destination + '/oid'), 303
end