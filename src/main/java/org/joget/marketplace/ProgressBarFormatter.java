package org.joget.marketplace;

import org.joget.apps.app.service.AppUtil;
import org.joget.apps.datalist.model.DataList;
import org.joget.apps.datalist.model.DataListColumn;
import org.joget.apps.datalist.model.DataListColumnFormatDefault;

public class ProgressBarFormatter extends DataListColumnFormatDefault {

    @Override
    public String format(DataList dataList, DataListColumn column, Object row, Object value) {
        String columnValue = (String) value;
        String bgColor = getPropertyString("bgColor");
        String pbColor = getPropertyString("pbColor");
        String fontColor = getPropertyString("fontColor");
        String minValue = getPropertyString("minValue");
        String maxValue = getPropertyString("maxValue");
        String hideNum = getPropertyString("hideNum");
        String showPercentage = getPropertyString("showPercentage");
        Integer percentValue = 100;
        if(Integer.valueOf(columnValue) <= Integer.valueOf(maxValue)){
            percentValue = (Integer.valueOf(columnValue) * 100 / (Integer.valueOf(maxValue) - Integer.valueOf(minValue)));
        }

        StringBuilder html = new StringBuilder();
        if (columnValue != null && !columnValue.isEmpty()) {
            html.append("<div class=\"progress\"");
            if (bgColor != null && !bgColor.isEmpty()) {
              html.append(" style=\"background-color:" + bgColor);
            }

            html.append("\"><div class=\"progress-bar progress-bar-striped\" role=\"progressbar\" aria-valuenow=\"" 
            + columnValue + "\" aria-valuemin=\"" + minValue + "\" aria-valuemax=\"" + maxValue + "\" style=\"width:" + percentValue + "%;");

            if (pbColor != null && !pbColor.isEmpty()) {
                html.append("background-color:" + pbColor + ";");
            }
            if (fontColor != null && !fontColor.isEmpty()) {
                html.append("color:" + fontColor + "\"");
            }

            if(hideNum.equalsIgnoreCase("true")){
                html.append("\"></div></div>");
            } else {
                if(showPercentage.equalsIgnoreCase("true")){
                    html.append("\">" + percentValue + "%</div></div>");
                } else {
                    html.append("\">" + columnValue + "</div></div>");
                }
            }

            return html.toString();
        }
        return (String) value;
    }

    @Override
    public String getName() {
        return "Progress Bar Formatter";
    }

    @Override
    public String getVersion() {
        return "7.0.1";
    }

    @Override
    public String getDescription() {
        return "To display a progress bar based on the column value";
    }

    @Override
    public String getLabel() {
        return "Progress Bar Formatter";
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
