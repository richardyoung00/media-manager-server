<%@ page import="java.io.File,java.io.FilenameFilter,java.util.Arrays"%>
<%@ page import="MediaManager.util.Util" %>
<%


    String path = request.getParameter("path");
    String id = request.getParameter("id");
    String newValue = request.getParameter("newValue");
    String oldValue = request.getParameter("oldValue");

    //  / ? < > \ : * | ”
    if (newValue==null || newValue.isEmpty()
            || Util.stringContains(newValue,"/","\\","?",">","<",":","*","|","\"","'")){
        out.println(oldValue + Util.jMessage("Invalid file name"));
        return;
    }

    File fOld = new File(path);
    if (!fOld.exists()){
        out.println(oldValue + Util.jMessage("File does not exist"));
        return;
    }
    fOld.setWritable(true);
    if (!fOld.canWrite()){
        out.println(oldValue + Util.jMessage("Cannot write to the file"));
        return;
    }

    String newPath = Util.replaceLast(path, oldValue, newValue);
    File fNew = new File(newPath);
    String er = "";
    boolean success=true;
    try{
        success = fOld.renameTo(fNew);

    }catch(Exception e) {
        er = e.getLocalizedMessage();
    }


    if (!success) {
        out.println(er+ Util.jMessage("Could not rename file"));
    }else {
        out.println(newValue);
    }




%>