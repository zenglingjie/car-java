function initAce(d){
	ace.require("ace/ext/language_tools");
    var editor = ace.edit(d);
    editor.setOptions({
        enableBasicAutocompletion: true,
        enableSnippets: true,
        enableLiveAutocompletion: true
    });
    editor.setTheme("ace/theme/monokai");
    editor.getSession().setMode("ace/mode/python");
    return editor;
}
