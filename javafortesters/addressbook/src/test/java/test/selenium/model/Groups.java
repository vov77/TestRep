package test.selenium.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

//делаем класс, который работает как Set, но расширяет его нашими методами
// библиотека Guava, реализует шаблон Decorator - все вызовы методов делигируются объекту - реальному списку
// или множеству, который вложен внутрь обертки, в которой можно реализовывать свои методы,
//For all the various collection interfaces, Guava provides Forwarding abstract classes to simplify using the decorator pattern.
//которые взаимодействуют с объектом, которому все делегируется (можно и переопределять существующие методы)
// в Guava есть набор вспомогательных классов для построения коллекций
// с расширенным набором методов ForwardingSet/ForwardingList https://github.com/google/guava/wiki/CollectionHelpersExplained
// указываем, что это список объектов типа GroupData, имплементируем метод delegate()

public class Groups extends ForwardingSet <GroupData> {

  //объявляем объект, которому делегируются стандартные и доп методы - decorated object - делегат
  private Set<GroupData> delegate;

  // имплементируем и переопределяем метод delegate() - возвращает объект delegate
  //override to return the decorated object. Each of the other methods delegate directly to the delegate:
  // so, for example, ForwardingList.get(int) is simply implemented as delegate().get(int).
  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }
// строим копию существующего объекта
  public Groups(Groups groups) {
    // берем множество из существующего объекта, переданного в параметре, строим новое множество с теми же элементами
    //присваиваем новое множество в качестве атрибута в новый объект
    this.delegate = new HashSet<GroupData>(groups.delegate);
  }

  public Groups() {
    delegate = new HashSet<GroupData>();
  }

// добавляем свои методы - передаем объект типа GroupData, возвращаем объек типа Groups чтобы делать цепочки/каскады
// делаем копию - старый объект остается неизменным, возвращаем новый объект с добавленной новой группой
// копирование дает возможность работать и со старой и с новой группами
  public Groups withAdded(GroupData group)  {
    //this - переменная, в которой хранится ссылка на объект, вызывающий данный метод..
    //метод всегда может получить данные из своего объекта или вызвать другой нестатический метод этого же объекта.
     Groups groups = new Groups(this); // строим копию существубщего объекта
     groups.add(group); // в копию добавляем объект из параметра
     return groups; // возвращаем копию с добавленной группой
  }
  public Groups without(GroupData group)  {
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;
  }

}
