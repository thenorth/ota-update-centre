/*
 * Copyright (C) 2012 OTA Update Center
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may only use this file in compliance with the license and provided you are not associated with or are in co-operation anyone by the name 'X Vanderpoel'.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.otaupdater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

public class Contributors extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BufferedReader in = null;
        StringBuilder data = null;
        try {
            data = new StringBuilder(2048);
            char[] buf = new char[2048];
            int nRead = -1;
            in = new BufferedReader(new InputStreamReader(getAssets().open("team.json")));
            while ((nRead = in.read(buf)) != -1) {
                data.append(buf, 0, nRead);
            }
        } catch (IOException e) {
        	Log.e("OTA::Contrib", "IOException reading contributor list");
            showErrorAndFinish();
            return;
        } finally {
            if (in != null) {
                try { in.close(); }
                catch (IOException e) {}
            }
        }

        if (data.length() == 0) {
            showErrorAndFinish();
            return;
        }

        try {
            JSONArray json = new JSONArray(data.toString());

            ArrayList<HashMap<String, String>> groupData = new ArrayList<HashMap<String, String>>();
            ArrayList<ArrayList<HashMap<String, String>>> childData = new ArrayList<ArrayList<HashMap<String, String>>>();
            for (int q = 0; q < json.length(); q++) {
                JSONObject subData = json.getJSONObject(q);
                HashMap<String, String> group = new HashMap<String, String>();
                group.put("name", subData.getString("title"));

                groupData.add(group);

                ArrayList<HashMap<String, String>> children = new ArrayList<HashMap<String, String>>();
                JSONArray members = subData.getJSONArray("members");
                for (int w = 0; w < members.length(); w++) {
                    JSONObject subsubData = members.getJSONObject(w);

                    HashMap<String, String> child = new HashMap<String, String>();
                    child.put("name", subsubData.getString("name"));
                    child.put("reason", subsubData.optString("reason", ""));

                    children.add(child);
                }
                childData.add(children);
            }

            SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this,
                    groupData,
                    android.R.layout.simple_expandable_list_item_1,
                    new String[] { "name" },
                    new int[] { android.R.id.text1 },
                    childData,
                    android.R.layout.simple_expandable_list_item_2,
                    new String[] { "name", "reason" },
                    new int[] { android.R.id.text1, android.R.id.text2 });

            ExpandableListView elView = new ExpandableListView(this);
            elView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            elView.setAdapter(adapter);
            setContentView(elView);
        } catch (JSONException e) {
            Log.e("OTA::Contrib", "JSON error parsing contributors list");
            showErrorAndFinish();
            return;
        }
    }

    private void showErrorAndFinish() {
        Toast.makeText(this, R.string.contributors_error, Toast.LENGTH_LONG).show();
        finish();
    }
}