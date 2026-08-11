if (_input1==null || _input1.isEmpty()) {
	return "";
};

if (_input1.isDouble()) {
	if (_input1.toDouble() >= 0)
		return 1000*Math.random();
	else
		return -1000*Math.random();
};

return 1000000*Math.random();
