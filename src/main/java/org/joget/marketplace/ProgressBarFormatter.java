package org.joget.marketplace;

import org.joget.apps.app.service.AppUtil;
import org.joget.apps.datalist.model.DataList;
import org.joget.apps.datalist.model.DataListColumn;
import org.joget.apps.datalist.model.DataListColumnFormatDefault;
import org.joget.commons.util.LogUtil;

public class ProgressBarFormatter extends DataListColumnFormatDefault {

    @Override
    public String format(DataList dataList, DataListColumn column, Object row, Object value) {
        String columnValue = (String) value;
        if (columnValue != null && !columnValue.isEmpty()) {
            String maxValue = getPropertyString("maxValue");
            try {
                int intVal = Integer.parseInt(columnValue);
                int intMax = Integer.parseInt(maxValue);
                double percentage = intVal * 100 / intMax;
                if (percentage == 100) {
                    return "<div class=\"progress\"><div class=\"progress-bar progress-bar-success progress-bar-striped\" role=\"progressbar\"aria-valuenow=\"100\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width:100%\">100%</div></div>";
                } else {
                    return "<div class=\"progress\">  <div class=\"progress-bar progress-bar-striped active\" role=\"progressbar\"  aria-valuenow=\"" + value + "\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width:" + value + "%\">    " + value + "%  </div></div>";
                }
            } catch (Exception e) {
                LogUtil.error(getClassName(), e, e.getMessage());
            }
        }
        return (String) value;
    }

    @Override
    public String getName() {
        return "Progress Bar Formatted";
    }

    @Override
    public String getVersion() {
        return "7.0.0";
    }

    @Override
    public String getDescription() {
        return "To display a progress bar based on the column value";
    }

    @Override
    public String getLabel() {
        return "Progress Bar Formatted";
    }

    @Override
    public String getClassName() {
        return this.getClass().getName();
    }

    @Override
    public String getPropertyOptions() {
        return AppUtil.readPluginResource(getClass().getName(), "/properties/progressBarFormatter.json", null, true, "message/ProgressBarFormatter");
    }

}
