import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'populationFormat',
  standalone: true
})
export class PopulationFormatPipePipe implements PipeTransform {

   transform(value: number): number {
    if (value >= 1000000) {
      return (value / 1000000) ;
    } else if (value >= 1000) {
      return (value / 1000) ;
    }
    return value;
  }

}