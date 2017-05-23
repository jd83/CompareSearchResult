system = require('system')     
address = system.args[1]; 
var page = require('webpage').create();     
var url = address;        
page.open(url, function (status) {         
    if (status !== 'success') {     
        console.log('failed!');     
    } else {                  
        console.log(page.content);     
    }        
    phantom.exit();     
});  