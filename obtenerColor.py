import urllib2
import simplejson
import sys

url = sys.argv[1]
req = urllib2.Request(url)
opener = urllib2.build_opener()
f = opener.open(req)
j = simplejson.load(f)
print j['color']
