# Android BindLibrary

Introduction of using data binding.

Gradle:

```gradle
compile 'com.noisyz.bindlibrary:DataBindingLibrary:1.0.5'
```
Maven:
```maven
<dependency> 
    <groupId>com.noisyz.bindlibrary</groupId> 
    <artifactId>DataBindingLibrary</artifactId> 
    <version>1.0.5</version>
    <type>pom</type> 
</dependency>
```
# 1. Prepare layouts. 

In your views set a tag with a your property key.

```xml
        <ImageView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:adjustViewBounds="true"
            android:tag="@string/key_image"/>

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:tag="@string/key_title" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:tag="@string/key_genre" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:tag="@string/key_date" />
```

strings.xml
```xml
    <string name="key_title">title</string>
    <string name="key_genre">genre</string>
    <string name="key_date">publicationDate</string>
    <string name="key_image">imageUrl</string>
```

# 2. Prepare model. 
## 2.1. Work with object fields.
### 2.1.1 Simple @Field.

You can use simple @Field annotation and set propertyType what you need:

TEXT, TEXT_RES,  FLOAT_TEXT, DOUBLE_TEXT, BOOLEAN, PROGRESS, RATING, RATING_CHANGEABLE, PROGRESS_CHANGEABLE, VISIBILITY, ENABLED

```java
public class Movie{
...
    @Field(propertyType.TEXT)
    private String genre, title;
...
}
```
Remember, if you use @Field or another field annotation, then your propertyKey is a name of field, what is annoted.

### 2.1.2. Converters.
Now we need to show date in text, but our view must receive a primitive value.
```java
...
@Field(value = propertyType.TEXT, twoWayConverter = @Conversion(DateConverter.class))
    private Date publicationDate;
...
```


DateConverter.java
```java
public class DateConverter extends TwoWayConverter<Date, String> {

   private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();

   @Override
   public String getConvertedValueToUI(Date date) {
      return DATE_FORMAT.format(date);
   }

   @Override
   public Date getConvertedValueToObject(String s) {
      Date date = null;
      try {
         date =  DATE_FORMAT.parse(s);
      } catch (ParseException e) {
         e.printStackTrace();
      }
      return date;
   }
}
```
We can select a two types of conversions. 

1. Two way conversion - @Conversion with class, which is extented TwoWayConverter<T,V>.java.

1. One way conversion - @ConvertToObject and @ConvertToUI with class, which implement interface Converter<T,V>.java.

### 2.1.3. Showing image.
Use @ImageField. 

```java
@ImageField(GlideImageProvider.class)
    private String imageUrl;
```
@ImageField receives a ImageProvider to know how load your image in your ImageView.

Supported providers: GlideImageProvider, FileImageProvider, PicassoImageProvider, ResourceImageProvider.

You can create custom ImageProvider with extending AsyncImageProvider or SyncImageProvider.

GlideImageProvider.java

```java
public class GlideImageProvider extends AsyncImageProvider<String>{

    @Override
    public void loadBitmap(String url) {
            Glide.with(getView().getContext()).load(url).crossFade().into(getView());
    }
}
```
### 2.1.4. @AdapterViewField
Use for selectable views like Spinner.
```java
@AdapterViewField(resourceArray = R.string.variants, layoutResID = R.layout.item)
private int selectedItem;
```    
layoutResID must have TextView as view parent.

## 2.2. Work with object methods.
### 2.2.1. @GetterMethod, @SetterMethod.
You can work with getters/setters method of object.
It's look like:

```java
...
    @GetterMethod(value = propertyType.TEXT, propertyKey = "genre" )
    public String getGenre() {
        return genre;
    }

    @SetterMethod(value = propertyType.TEXT, propertyKey = "genre")
    public Movie setGenre(String genre) {
        this.genre = genre;
        return this;
    }
...
```
But that is looking like hardcode, Right?:) 
I think that's can be better:

```java
...
    @GetterMethod(value = propertyType.TEXT, propertyKeyResId = R.string.key_genre)
    public String getGenre() {
        return genre;
    }

    @SetterMethod(value = propertyType.TEXT, propertyKeyResId = R.string.key_genre)
    public Movie setGenre(String genre) {
        this.genre = genre;
        return this;
    }
...
```
Or you can use constants like:
```java
public static final String KEY_GENRE = "genre"
```
### 2.2.2. @ImageGetter, @SimpleAdapterViewGetter, @SimpleAdapterViewSetter. 
They work like their analogies @ImageField, @AdapterViewField.
# 3. Use binding. 
## 3.1. BindingManager, ObjectViewBinder, DataUpdatedCallback.
### 3.1.1. ObjectViewBinder.
You can use ObjectViewBinder with your object instance on the next way:

```java
public class DetailActivity extends AppCompatActivity {

   private static final String EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID";

   public static Intent buildIntent(final Context context, final int movieId) {
      Intent intent = new Intent(context, DetailActivity.class);
      intent.putExtra(EXTRA_MOVIE_ID, movieId);
      return intent;
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      final int movieId = getIntent().getIntExtra(EXTRA_MOVIE_ID, 0);

      final Movie movie = MovieStore.getAllMovies().get(movieId);
      setContentView(R.layout.activity_detail);
      
      //This is all what we need for bind our object values and current view.
      new ObjectViewBinder(movie).registerView(this).bindUI();
   }
}
```

ObjectViewBinder has 2 methods for registering view.
- registerView(View view);
- registerView(Activity activity);
 
He support handling click event.
- setOnElementClickListener(int elementId, OnClickListener onClickListener);
- setOnElementsClickListener(int[] elementId, OnClickListener onClickListener);

```java
...
new ObjectViewBinder(movie).registerView(this).setOnElementClick(R.id.save_button, new View.OnClickListener() {
         @Override
         public void onClick(View view) {
              save();
         }
      }).bindUI();
...
```


### 3.1.2. BindingManager.
Use BindingManager to bind binders;)
```java
...
BindingManager.newInstance().
newBinder("CurrentMovieBinder", new ObjectViewBinder(movie).registerView(SomeMovieView)).
newBinder("CurrentUserBinder", new ObjectViewBinder(user).registerView(SomeUserView)).
bindUI();
...
```

### 3.1.3. DataUpdatedCallback<T, V>
You can listen changes in your object instance.
For thats you have 3 ways:
####A) Listen BindingManager:
```java
...
BindingManager.newInstance().
newBinder("CurrentMovieBinder", new ObjectViewBinder(movie).registerView(SomeMovieView)).
newBinder("CurrentUserBinder", new ObjectViewBinder(user).registerView(SomeUserView)).
setDataUpdatedCallback(new DataUpdatedCallback() {
        /**That means so something in your objects has been changed
         @UIBinder - where changed.
         @object - what exactly object changed.
         @propertyName - what property changed.
         @value - new value of property.
          */
         @Override
         public void onDataUpdated(UIBinder uiBinder, Object object, String propertyName, Object value) {
            
         }
      });
bindUI();
...
```
####B) Listen ObjectViewBinder:
```java
...
new ObjectViewBinder(movie).registerView(SomeMovieView).
setDataUpdatedCallback(new DataUpdatedCallback<Movie, Object>() {
        /**
         all the same, which in previous example, but here we have our specific object.
          */
         @Override
         public void onDataUpdated(UIBinder uiBinder, Movie movie, String propertyName, Object value) {
         }
      }.
bindUI();
...
```

####C) "We need to go deeper". (©, "Inception"):
```java
...
new ObjectViewBinder(movie).registerView(SomeMovieView).
setDataUpdatedCallback("publicationDate", new DataUpdatedCallback<Movie, Date>() {
        /**
         Now we can listen when  a specific property of our object will be changed
          */
         @Override
         public void onDataUpdated(UIBinder uiBinder, Movie movie, String propertyName, Date newDate) {
         
         }
      }.
bindUI();
...
```


## 3.2. Adapters.
Library support next adapters:
- RecyclerBindAdapter, which you can use with RecyclerView.
- BindAdapter, which extends BaseAdapter.
- ExpandableBindAdapter.

RecyclerBindAdapter's example
```java 
...
      RecyclerView list = (RecyclerView) findViewById(R.id.list);
      
      list.setLayoutManager(new LinearLayoutManager(this));
      
      RecyclerBindAdapter adapter = new RecyclerBindAdapter(MovieStore.getAllMovies(), R.layout.list_item)
              .setOnItemClickListener(new OnItemClickListener<Movie>() {
                 @Override
                 public void onItemClick(View convertView, int position, Movie movie) {
                    startActivity(DetailActivity.buildIntent(getApplicationContext(), position));
                 }
              });
              
      list.setAdapter(adapter);
...
```
All of adapters support OnClickListener by next methods.
- setOnItemClickListener(OnItemClickListener<T> onItemClickListener)
- setOnElementClickListener(int elementId, OnElementClickListener<T> onElementClickListener)
- setOnElementsClickListener(int[] elementIds, OnElementClickListener<T> onElementClickListener)

They also support different layouts. 
Use LayoutResourceProvider for RecyclerBindAdapter and BaseLayoutResourceProvider for BindAdapter.

RecyclerBindAdapter:
```java
...
RecyclerBindAdapter adapter = new RecyclerBindAdapter(MovieStore.getAllMovies(), new LayoutResourceProvider<Movie>() {
         @Override
         public int getItemViewType(Movie movie) {
            return movie.getType();
         }

         @Override
         public int getLayoutResourceID(int type) {
            switch(type){
               case Movie.HORROR:
                  return R.layout.horror;
               case Movie.FAMILY:
                  return R.layout.family;
               case Movie.SPORT:
                  return R.layout.sport;
               default: return R.layout.default_item;
            }
         }
      });
...
```

BindAdapter:

```java
...
BindAdapter adapter = new BindAdapter(MovieStore.getAllMovies(), new BaseLayoutResourceProvider<Movie>() {
         @Override
         public int getLayoutCount() {
            return YOUR_LAYOUT_COUNT;
         }

         @Override
         public int getItemViewType(Movie movie) {
            return movie.getType();
         }

         @Override
         public int getLayoutResourceID(int type) {
            switch(type){
               case Movie.HORROR:
                  return R.layout.horror;
               case Movie.FAMILY:
                  return R.layout.family;
               case Movie.SPORT:
                  return R.layout.sport;
               default: return R.layout.default_item;
            }
         }
      });
...
```

# 4. Custom ViewWrappers.
For use binding in custom view you need:
##4.1. 
First you need to use custom viewbinder for that. Implement IViewBinder.java.

```java
...

public class CustomViewBinder implements IViewBinder<String, CustomView> {
    @Override
    public void bindUI(String s, CustomView customView) {
        customView.setValue(s);
    }
}
...
```
If your view needs change object value, then you must extend ViewBinder
```java
...
public class CustomViewBinder extends ViewBinder<String, CustomView> implements CustomView.OnCustomViewListener {
@Override
    public void bindUI(String s, CustomView customView) {
        customView.setValue(s);
    }

    @Override
    public void addListeners(CustomView customView) {
        customView.setCustomViewListener(this);
    }

    @Override
    public void removeListeners(CustomView customView) {
        customView.setCustomViewListener(this);
    }

    @Override
    public String getViewValue(CustomView customView) {
        return customView.getCurrentValue();
    }

    @Override
    public void onCustomViewSomethingChanged() {
        bindObject();
    }
...
```
##4.2. Update our object class with @CustomField or @CustomGetterMethod/@CustomSetterMethod.
They need a Class<? extends IViewBinder>. 
With the rest they work as well as analogues
```java
...
@CustomField(CustomViewWrapper.class)
private CustomProperty customProperty;
...
```

#5. Supporting included objects.
Use @ObjectField() or @ObjectGetterMethod().

#Licence

Copyright 2016 Oleg Bohdan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

