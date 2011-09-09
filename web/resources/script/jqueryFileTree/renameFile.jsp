<%@ page import="MediaManager.util.Util,java.io.File" %>
<%
    String path = request.getParameter("path");
    String id = request.getParameter("id");
    String newValue = request.getParameter("newValue");
    String oldValue = request.getParameter("oldValue");

    //  / ? < > \ : * | ”
    if (newValue == null || newValue.isEmpty()
            || Util.stringContains(newValue, "/", "\\", "?", ">", "<", ":", "*", "|", "\"", "'")) {
        out.println(oldValue + Util.jMessage("The file name you have entered contains invalid characters."));
        return;
    }

    File fOld = new File(path);
    if (!fOld.exists()) {
        out.println(oldValue + Util.jMessage("File does not exist. Please refresh and try again."));
        return;
    }

    String newPath = Util.replaceLast(path, oldValue, newValue);
    File fNew = new File(newPath);

    boolean success = false;
    try {
        success = fOld.renameTo(fNew);
    } catch (Exception e) {
    }


    if (!success) {
        out.println(oldValue + Util.jMessage("Could not rename the selected file. Possibly the files permissions need to be changed"));
    } else {
        out.println(newValue);
    }


%>