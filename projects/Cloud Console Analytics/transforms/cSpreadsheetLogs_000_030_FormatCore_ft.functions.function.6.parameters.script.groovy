def log = _input1;
def username = log.subSequence(log.lastIndexOf("for user ") + 8, log.length());
return _input2+"_"+username;
