package it.uniroma3.tags;

import java.util.List;

import it.uniroma3.model.PhotoModel;

public class PhotoContainsTag {
	   public static boolean contains(List<PhotoModel> list, PhotoModel o) {
		      return list.contains(o);
		   }
}
