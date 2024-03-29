package lance.liang.group.latina;

import java.util.*;

class MessageData {
	public int mid, gid, send_time, status = ItemBeanChat.DONE;
	public String username, text, type, head, tag;
	MessageData() {}
	MessageData(ItemBeanChat item) {
		mid = item.mid; gid = item.gid; send_time = item.send_time; status = item.status;
		username = item.username; type = item.type; head = item.head_url; tag = item.tag;
		text = item.message;
	}
	MessageData(int _mid, int _gid, int _send_time, String _username, String _text, String _type, String _head) {
		mid = _mid; gid = _gid; send_time = _send_time; username = _username; text = _text; type = _type; head = _head;
	}
	public MessageData setTag(String tag) {
		this.tag = tag;
		return this;
	}
	public MessageData setStatus(int status) {
		this.status = status;
		return this;
	}
}

class RoomData {
	public int gid, create_time, member_number, last_post_time;
	public String name, head, room_type;
}

/*
 {
 'uid': info[0], 'username': info[1], 'email': info[2],
 'head': info[3], 'motto': info[4], 'rooms': rooms, "user_type": info[6]
 }*/

class PersonData {
	public String username = "", head = "", user_type = "", motto = "", email = "", 
				auth = "";	// use when you login
	public int uid = 0, last_active_time = 0;
	//public List<Integer> rooms = new ArrayList<Integer>();
	public int[] rooms = new int[] {1};
}

class UploadData {
	public String filename, etag, url;
}

class FileData {
	public String username, url, filename;
}

class PreUpload {
	public String url;
}

class FontOption {
	public String font_family = "微软雅黑";	
	public int font_size = 10;
}
	

class FontSetting {
	public String message = "[--font-option--]";
	public FontOption option = new FontOption();
}

class ShiCiToken {
	public String data, status;
}

class ShiCiData {
	public static class Data {
		public static class Origin {
			public String title, dynasty, author;
			public List<String> content, translate, matchTags;
		}
		public String id, content;
		public int popularity;
		public Origin origin;
	}
	public String status, token, ipAddress, errMessage;
	public int errCode;
	public Data data;
}

public class ResultData
{
	public static class Data {
//		public static class NewMessages {
//			public int gid;
//			public int lastest_mid;
//		}
//		public static class Info {
//			public int gid, create_time, member_number, last_post_time;
//			public String name, head;
//		}
		public static class UploadResult {
			public String filename, etag, url;
		}
		public List<MessageData> message;
		public RoomData info;
//		public String auth;
//		public String head;
//		public String username;
		public PersonData user_info;
		public List<RoomData> room_data;
		public UploadResult upload_result;
		public List<FileData> files;
		public PreUpload pre_upload;
		public String version;
	}
	public int code;
	public String message;
	public Data data;
}


