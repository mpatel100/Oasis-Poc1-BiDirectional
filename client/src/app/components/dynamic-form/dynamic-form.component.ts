import {Component, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';
import { FormlyFieldConfig} from '@ngx-formly/core';
import { take } from 'rxjs';
import { AosisMappingService } from '@app/services/aosis-mapping.service';
@Component({  
  selector: 'dynamic-form',
  templateUrl: `./dynamic-form.component.html`,
  styleUrl: `./dynamic-form.component.scss`
})
export class DynamicFormComponent implements OnInit {
  form = new FormGroup({});
  model: any = {}; 
  fields: FormlyFieldConfig[] = [];
  formData: any;
  emailPattern = '[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}';

  constructor(private aosisMappingService: AosisMappingService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.aosisMappingService.getFormData()
    .pipe((take(1)))
      .subscribe({
        next: (response: any) =>{
            this.formData = response;
            this.defineControls();
            return response;
        },
        error: (e) => {
          console.error('Error reading the json file.', e);
          return e;
        },
        complete: () => console.info('complete') 
      });
  }

  defineControls():void {
    this.fields = this.formData.map((row: any) => {
      
      let fieldConfig: FormlyFieldConfig = {
      };
      // set hooks
      fieldConfig.hooks = {
        onInit: (field) => {
          const { form, model, options, props } = field;
        },
      };

      row['attribute'] =  row['attribute']?.toString().trim();
      row['controlType'] = row['controlType']?.toString().trim();
      row['value'] = row['value']?.toString().trim(); 

      // setting key
      fieldConfig.key = row['attribute'];
      // setting default value
      fieldConfig.defaultValue = row['value'];
      fieldConfig.id = row['attribute'];
      const rowType = row['controlType'];

      switch (rowType) {
        case 'varchar':
          fieldConfig.type = row['attribute'] === 'gender' ? 'radio' : 'input';
          fieldConfig.key = row['attribute'];
          
          /**
           * TODO: we need to check separate data type for radio button group, right now it is as varchar
           * so we need to add check for gender specifically
           */
          if(row['attribute'] === 'gender') {
            fieldConfig.props =   {
              name: row['attribute'],
              label: row['labelName'],
              type: 'radio',
              tooltip: {
                content: row['tooltip']
              },  
              options: [{ value: 'Male', key: 'M' }, { value: 'Female', key: 'F' }],
              required: true
            };
          } else  if(row['attribute'] === 'email') {
            fieldConfig.props =   {
              label: row['labelName'],
              type: 'input',  
              tooltip: {
                content: row['tooltip']
              },
              required: true,
              pattern: this.emailPattern
            };
            fieldConfig.validation = {
                messages: {
                    pattern: (error: any, field: FormlyFieldConfig) => `Should have valid email address`
                }
            };
          } else {
            fieldConfig.props = {
              label: row['labelName'],
              type: 'input',  
              tooltip: {
                content: row['tooltip']
              },
              required: true,
              minLength: 2
            };
          }
        break;
        case 'bool':
          // TODO: add for checkbox
          break;
        case 'date':
          fieldConfig.type = 'date';
          fieldConfig.props = {
            label: row['labelName'],
            type: 'date',
            placeholder: 'yyyy-mm-dd',
            format:'yyyy-mm-dd',
            tooltip: {
                content: row['tooltip']
              },
            required: true,
            datepickerOptions: {
             // min: new Date()
            },
          }
          break;
        case 'double':
          fieldConfig.type = 'number';
          fieldConfig.props = {
            label: row['labelName'],
            type: 'number',
            tooltip: {
                content: row['tooltip']
              },
            required: true
          };
          break;
        default:
         break;
      }
  
      return fieldConfig;
    });
  }

  onSubmit() {
    if(this.form.invalid) return;
    console.log(this.model);
    if(this.formData) {
      const outputData = this.formData.map((row: any) => {
        if(this.model[row.attribute]) {
          row.value = this.model[row.attribute];
        }
        row['timestamp'] = new Date();
        return row;
      });
      var a = document.createElement('a');
      a.setAttribute('href', 'data:json;charset=utf-u,'+encodeURIComponent(JSON.stringify(outputData)));
      a.setAttribute('download', 'output.json');
      a.click();
    }
  }

  onCancel() {
    this.form.reset();
    this.model = {};
  }
}